package elnaggar.usertypemostionlayoutdemo

import android.content.Context
import android.graphics.Canvas
import android.os.Parcel
import android.os.Parcelable
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.motion.widget.TransitionAdapter
import elnaggar.usertypemostionlayoutdemo.databinding.UserTypeViewBinding

class UserTypeView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : MaterialMotionLayout(context, attrs, defStyleAttr) {

    private val binding: UserTypeViewBinding =
        UserTypeViewBinding.inflate(LayoutInflater.from(context), this)

    var isAnonymousSelected = true
    private var mOnAnonymousSelected: (() -> Unit)? = null
    private var mOnEmployeeSelected: (() -> Unit)? = null

    init {
        setAllCornersRadius(24.dp(context))
        setStrokeWidth(1.dp(context))
        setStrokeColor(R.color.colorSecondary)
        setBackgroundColorRes(R.color.white)

        setTransitionListener(object : TransitionAdapter() {
            override fun onTransitionCompleted(p0: MotionLayout?, currentState: Int) {
                when (currentState) {
                    R.id.user_type_start_state -> {
                        isAnonymousSelected = true
                        mOnAnonymousSelected?.invoke()
                    }
                    R.id.user_type_end_state -> {
                        isAnonymousSelected = false
                        mOnEmployeeSelected?.invoke()
                    }
                }
            }
        })

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        when(this.currentState){
            R.id.user_type_start_state -> {
                //Timber.tag("UserTypeView").d("user_type_start_state")

            }
            R.id.user_type_end_state -> {
            //Timber.tag("UserTypeView").d("user_type_end_state")
            }

        }
    }

    fun setOnAnonymousSelected(listener: () -> Unit) {
        mOnAnonymousSelected = listener
    }

    fun setOnEmployeeSelected(listener: () -> Unit) {
        mOnEmployeeSelected = listener
    }

     override fun onSaveInstanceState(): Parcelable? {
         val superState = super.onSaveInstanceState()
         val mState = SavedState(superState)
         mState.isAnonymousSelected = isAnonymousSelected
         return mState
     }

     override fun onRestoreInstanceState(state: Parcelable?) {
         val mState = state as SavedState
         super.onRestoreInstanceState(mState.superState)

         isAnonymousSelected = mState.isAnonymousSelected

         progress = if (isAnonymousSelected) {
             0f
         } else {
             1f
         }
     }

    private class SavedState : BaseSavedState {

        var isAnonymousSelected: Boolean = true

        internal constructor(superState: Parcelable?) : super(superState) {}

        private constructor(input: Parcel) : super(input) {
            isAnonymousSelected = input.readInt() != 0
        }

        override fun writeToParcel(out: Parcel, flags: Int) {
            super.writeToParcel(out, flags)
            out.writeInt(if (isAnonymousSelected) 1 else 0)
        }

        companion object {
            @JvmField
            val CREATOR = object : Parcelable.Creator<SavedState> {
                override fun createFromParcel(source: Parcel): SavedState {
                    return SavedState(source)
                }

                override fun newArray(size: Int): Array<SavedState?> {
                    return arrayOfNulls(size)
                }
            }
        }
    }
}

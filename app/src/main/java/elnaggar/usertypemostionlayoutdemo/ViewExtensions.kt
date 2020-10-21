package elnaggar.usertypemostionlayoutdemo

import android.content.Context
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Dimension

fun ViewGroup.inflater(): LayoutInflater = LayoutInflater.from(context)

fun ViewGroup.inflate(resource: Int, attachToRoot: Boolean = false): View =
    inflater().inflate(resource, this, attachToRoot)

fun View.getString(resId: Int) = context.getString(resId)

fun Int.dp(context: Context): Float = dpToPx(context, this)
fun dpToPx(context: Context, @Dimension(unit = Dimension.DP) dp: Int): Float {
    val r = context.resources
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp.toFloat(),
        r.displayMetrics
    )
}

import android.content.Intent
import android.net.Uri
import android.os.Build

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()

//internal actual fun openUrl(url: String?) {
//    val uri = url?.let { Uri.parse(it) } ?: return
//    val intent = Intent().apply {
//        action = Intent.ACTION_VIEW
//        data = uri
//        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//    }
//    .startActivity(intent)
//}
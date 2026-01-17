package com.pascal.aniqu.utils

import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSOperationQueue
import platform.Foundation.NSSearchPathForDirectoriesInDomains
import platform.Foundation.NSUserDomainMask
import platform.UIKit.UIActivityViewController
import platform.UIKit.UIAlertController
import platform.UIKit.UIAlertControllerStyleAlert
import platform.UIKit.UIApplication
import platform.UIKit.UIViewController
import platform.darwin.DISPATCH_TIME_NOW
import platform.darwin.dispatch_after
import platform.darwin.dispatch_async
import platform.darwin.dispatch_get_main_queue
import platform.darwin.dispatch_time
import platform.Foundation.NSBundle

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual object AppInfo {
    actual val versionName: String
        get() = NSBundle.mainBundle.objectForInfoDictionaryKey("CFBundleShortVersionString") as? String ?: ""

    actual val versionCode: String
        get() = NSBundle.mainBundle.objectForInfoDictionaryKey("CFBundleVersion") as? String ?: ""

    actual val appId: String
        get() = NSBundle.mainBundle.bundleIdentifier ?: ""
}

actual fun showToast(msg: String) {
    dispatch_async(dispatch_get_main_queue()) {

        val rootVC: UIViewController =
            UIApplication.sharedApplication.keyWindow?.rootViewController ?: return@dispatch_async

        val alert = UIAlertController.alertControllerWithTitle(
            title = null,
            message = msg,
            preferredStyle = UIAlertControllerStyleAlert
        )

        rootVC.presentViewController(alert, animated = true, completion = null)

        dispatch_after(
            dispatch_time(DISPATCH_TIME_NOW, 2_000_000_000),
            dispatch_get_main_queue()
        ) {
            alert.dismissViewControllerAnimated(true, null)
        }
    }
}

actual fun actionShareUrl(url: String?) {
    if (url.isNullOrEmpty()) return

    val items: List<Any> = listOf(url)

    val activityVC = UIActivityViewController(
        activityItems = items,
        applicationActivities = null
    )

    val rootVC: UIViewController =
        UIApplication.sharedApplication.keyWindow?.rootViewController ?: return

    NSOperationQueue.mainQueue.addOperationWithBlock {
        rootVC.presentViewController(activityVC, animated = true, completion = null)
    }
}

actual fun downloadDirectory(): String =
    NSSearchPathForDirectoriesInDomains(
        NSDocumentDirectory,
        NSUserDomainMask,
        true
    ).first() as String

package com.pascal.aniqu.utils

import platform.UIKit.*

actual fun setScreenOrientation(orientation: ScreenOrientation) {
    val value = when (orientation) {
        ScreenOrientation.LANDSCAPE -> UIInterfaceOrientationLandscapeRight
        ScreenOrientation.PORTRAIT -> UIInterfaceOrientationPortrait
    }

    UIDevice.currentDevice.setValue(value, forKey = "orientation")
    UIViewController.attemptRotationToDeviceOrientation()
}

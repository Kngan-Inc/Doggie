package com.doggie.app.util

import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import com.google.android.material.bottomnavigation.BottomNavigationView

class BottomNavigationViewBehavior: CoordinatorLayout.Behavior<BottomNavigationView>() {

    private var height = 0f

    override fun onLayoutChild(
        parent: CoordinatorLayout,
        child: BottomNavigationView,
        layoutDirection: Int
    ): Boolean {
        height = child.height.toFloat()
        return super.onLayoutChild(parent, child, layoutDirection)
    }

    override fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: BottomNavigationView,
        directTargetChild: View,
        target: View,
        axes: Int,
        type: Int
    ): Boolean {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL
    }

    override fun onNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: BottomNavigationView,
        target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        type: Int,
        consumed: IntArray
    ) {
        if(dyConsumed > 0) {
            slideUp(child)
        } else if(dyConsumed < 0) {
            slideDown(child)

        }
    }

    override fun onNestedPreScroll(
        coordinatorLayout: CoordinatorLayout,
        child: BottomNavigationView,
        target: View,
        dx: Int,
        dy: Int,
        consumed: IntArray,
        type: Int
    ) {
        if(dy > 0) {
            slideUp(child)
        } else if(dy < 0) {
            slideDown(child)
        }
    }

    private fun slideDown(child: BottomNavigationView) {
        child.clearAnimation()
        child.animate().translationY(0f).duration = 200
    }
    private fun slideUp(child: BottomNavigationView) {
        child.clearAnimation()
        child.animate().translationY(height).duration = 200
    }
}
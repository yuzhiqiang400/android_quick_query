public final class HomeBehavior extends AppBarLayout.Behavior {
    private boolean isPositive;

    public HomeBehavior() {
    }

    public HomeBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, AppBarLayout child, View target, int dxConsumed, int dyConsumed,
                               int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
    }

    @Override
    public boolean onNestedFling(CoordinatorLayout coordinatorLayout, AppBarLayout child, View target,
                                 float velocityX, float velocityY, boolean consumed) {
        return fling(coordinatorLayout, child, target, velocityX, velocityY, consumed);
    }

    private boolean fling(CoordinatorLayout coordinatorLayout, AppBarLayout child, View target,
                          float velocityX, float velocityY, boolean consumed) {
        if ((velocityY > 0 && !isPositive) || (velocityY < 0 && isPositive)) {
            velocityY = velocityY * -1;
        }
        if (target instanceof RecyclerView /*&& velocityY < 0*/) {
            RecyclerView recyclerView = (RecyclerView) target;
            if (isPositive) {
                consumed = true;
            } else {
                LinearLayoutManager llm = (LinearLayoutManager) recyclerView.getLayoutManager();
                int firstVisible = llm.findFirstCompletelyVisibleItemPosition();
                consumed = firstVisible != 0;
            }
        }
        Log.d("Fling", "consume:" + consumed);
        return super.onNestedFling(coordinatorLayout, child, target, velocityX, velocityY, consumed);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout parent, AppBarLayout child, View directTargetChild, View target, int nestedScrollAxes) {
        return super.onStartNestedScroll(parent, child, directTargetChild, target, nestedScrollAxes);
    }

    @Override
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, AppBarLayout abl, View target) {
        super.onStopNestedScroll(coordinatorLayout, abl, target);
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, AppBarLayout child,
                                  View target, int dx, int dy, int[] consumed) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
        isPositive = dy > 0;
        Log.d("Fling", "onNestedPreScroll " + dy);
    }
}

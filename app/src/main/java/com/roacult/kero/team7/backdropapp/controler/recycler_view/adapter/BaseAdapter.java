package com.roacult.kero.team7.backdropapp.controler.recycler_view.adapter;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.roacult.kero.team7.backdropapp.controler.PaginationCallBack;


/***
 * FOR PAGINATION
 */

public abstract class BaseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private boolean isLoading = false;
    private int loadingThreshold = 0;

    public boolean isLoading() {
        return isLoading;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }

    public void setLoaderListener(RecyclerView recyclerView, final PaginationCallBack paginationCallBack, final BaseAdapter mAdapter) {
        final LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
                if (!isLoading() && paginationCallBack != null ) {
                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount - loadingThreshold && firstVisibleItemPosition >= 0) {
                        setLoading(true);
                        mAdapter.addLoadingFooter();
                        paginationCallBack.loadMoreItems();
                    }
                }
            }
        });
    }

    protected abstract void addLoadingFooter();

}

package com.shinc.duobaohui.customview.imp;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lidroid.xutils.task.TaskHandler;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;
import com.shinc.duobaohui.R;


/**
 * 上拉加载更多；
 *
 * @author zpl
 */
public class LoadMoreListView extends ListView {

    private TaskHandler taskHandler;

    private View loadMoreView;

    public View getLoadMoreView() {
        return loadMoreView;
    }

    public void setLoadMoreView(View loadMoreView) {
        this.loadMoreView = loadMoreView;
    }


    private int firstItem;
    private int totalCount;
    private int BottomItemCount;
    private LoadMoreDateListener mListner;
    private ProgressBar loadMorePb;
    private TextView loadMoreTv;
    private boolean loadState = true;

    public LoadMoreListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    public LoadMoreListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public LoadMoreListView(Context context) {
        super(context);
        initView();
    }

    private void initView() {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View loadMore = inflater.inflate(R.layout.load_more_layout, null);
        loadMorePb = (ProgressBar) loadMore.findViewById(R.id.load_more_pb);
        loadMoreTv = (TextView) loadMore.findViewById(R.id.load_more_tv);

        this.loadMoreView = loadMore;
        this.addFooterView(loadMoreView, null, false);
        loadMoreView.setVisibility(View.GONE);
        this.setOnScrollListener(new PauseOnScrollListener(ImageLoader.getInstance(), true, true, new OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (BottomItemCount == totalCount && scrollState == SCROLL_STATE_IDLE) {
                    //已经下拉到最低端；此时显示加载更多的footer;
                    loadMoreView.setVisibility(View.VISIBLE);
                    if (mListner != null && loadState) {
                        mListner.loadMore();
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                firstItem = firstVisibleItem;
                totalCount = totalItemCount;
                BottomItemCount = firstVisibleItem + visibleItemCount;
                if (firstVisibleItem > 0 && onCallBack != null) {
                    onCallBack.showTitle(true);
                } else if (onCallBack != null) {
                    onCallBack.showTitle(false);
                }
            }
        }));

    }

    public void setLoadComplete() {
        loadMorePb.setVisibility(GONE);
        loadState = false;
        loadMoreTv.setText("已经到底了！");

    }

    public void setLoadReset() {
        loadMorePb.setVisibility(VISIBLE);
        loadState = true;
        loadMoreTv.setText("正在加载...");
        loadMoreView.setVisibility(GONE);
    }

    public void setLoadMoreListener(LoadMoreDateListener mListener) {
        this.mListner = mListener;
    }


    public interface LoadMoreDateListener {
        void loadMore();
    }

    private OnCallBack onCallBack;

    public void setCallback(OnCallBack onCallBack) {
        this.onCallBack = onCallBack;
    }

    public interface OnCallBack {
        void showTitle(boolean isShow);
    }


}

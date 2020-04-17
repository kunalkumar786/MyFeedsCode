package com.example.myfeeds;

import android.content.Context;
import android.widget.ListView;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.myfeeds.adapter.MyFeedAdapter;
import com.example.myfeeds.model.FeedModel;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    private MyFeedAdapter mAdapter;
    private FeedModel mItemView;
    private ArrayList<FeedModel> mItems;
    private ListView mListView;
    private Context context;

    @Test
    protected void setUp(){
        
        mItems = new ArrayList<FeedModel>();
        mItems.add(new FeedModel("url1", "title1", "description1"));
        mItems.add(new FeedModel("url2", "title2", "description2"));
        mItems.add(new FeedModel("url3", "title3", "description3"));
        mAdapter = new MyFeedAdapter(getContext(), mItems);
        mListView = new ListView(getContext());
    }
    @Test
    public void testGetItem() {
        FeedModel item = (FeedModel) mAdapter.getItem(0);
        assertEquals("url1", item.getImageHref());
        assertEquals("title1", item.getTitle());
        assertEquals("description1", item.getDescription());
        item = (FeedModel) mAdapter.getItem(2);
        assertEquals("url3", item.getImageHref());
        assertEquals("title3", item.getTitle());
        assertEquals("description3", item.getDescription());
    }
@Test
public void testConstruction() {
    String url = "http://test.com/item.jpg";
    String title = "This is the title";
    String description = "This is the description";
    FeedModel item = new FeedModel(url, title, description);
    assertEquals(url, item.getImageHref());
    assertEquals(title, item.getTitle());
    assertEquals(description, item.getDescription());
}

@Test
public void testSetItem() {
    FeedModel item = new FeedModel("url", "title", "description");
    mItemView.setItem(item);
    // TODO: test url
    assertEquals("title", mItemView.getTitle());
    assertEquals("description", mItemView.getDescription());
}


    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.myfeeds", appContext.getPackageName());
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
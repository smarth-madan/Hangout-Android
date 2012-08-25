package views;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import com.facebook.android.R;

import item.Item;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

public class ListItem extends LinearLayout {

	private Item resultItem;
	
	private ImageView resultItemimage;
	private TextView locationname;
	private TextView locationaddress;
	private ImageView ratingimage;
	private ImageView fbimage;
	private TextView fblikes;
	
	public ListItem(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		resultItemimage = (ImageView)findViewById(R.id.itemicon);
		locationname = (TextView)findViewById(R.id.locationname);
		locationaddress = (TextView)findViewById(R.id.locationaddress);
		ratingimage = (ImageView)findViewById(R.id.ratingicon);
		//fblikes = (TextView)findViewById(R.id.fblikes);
	}

	public void setresultItem(Item passeditem) {
		this.resultItem = passeditem;
		resultItemimage.setImageBitmap(passeditem.getItem_image());
		ratingimage.setImageBitmap(passeditem.getRating_image());
		//fblikes.setText(Integer.toString(passeditem.getFacebook_likes()));	
		locationname.setText(passeditem.getName());
		locationaddress.setText(passeditem.getAddress());
	}

	public Item getresultItem() {
		return resultItem;
	}

}

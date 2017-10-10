package course.examples.fragments.staticlayout;

import android.app.Fragment;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class QuotesFragment extends Fragment {

	private TextView quoteView = null;
	private ImageView imageView = null;
	private int currIdx = -1;
	private int quoteArrayLen;

	private static final String TAG = "QuotesFragment";

	public int getShownIndex() {
		return currIdx;
	}

	// Show the Quote string at position newIndex
	public void showQuoteAtIndex(int newIndex) {
		if (newIndex < 0 || newIndex >= quoteArrayLen)
			return;
		currIdx = newIndex;
		TypedArray picArray = getResources().obtainTypedArray(R.array.Pics);
		quoteView.setText(QuoteViewerActivity.quoteArray[currIdx]);
		imageView.setImageResource(picArray.getResourceId(currIdx, -1));
		picArray.recycle();
	}

	// Called to create the content view for this Fragment
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		// Inflate the layout defined in quote_fragment.xml
		// The last parameter is false because the returned view does not
		// need to be attached to the container ViewGroup
		return inflater.inflate(R.layout.quote_fragment, container, false);
	}

	// Set up some information about the mQuoteView TextView
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		quoteView = (TextView) getActivity().findViewById(R.id.quoteView);
		imageView = (ImageView) getActivity().findViewById(R.id.imageView);
		quoteArrayLen = QuoteViewerActivity.quoteArray.length;
	}

}

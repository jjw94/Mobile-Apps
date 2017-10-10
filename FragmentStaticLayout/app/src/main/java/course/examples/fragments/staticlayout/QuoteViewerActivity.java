package course.examples.fragments.staticlayout;

import android.app.Activity;
import android.content.res.TypedArray;
import android.os.Bundle;
import course.examples.fragments.staticlayout.TitlesFragment.ListSelectionListener;

public class QuoteViewerActivity extends Activity implements
		ListSelectionListener {

	public static String[] titleArray;
	public static String[] quoteArray;
	public static String[] picArray;
	private QuotesFragment detailsFragment;

	private static final String TAG = "QuoteViewerActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Get the string arrays with the titles and quotes
		titleArray = getResources().getStringArray(R.array.Titles);
		quoteArray = getResources().getStringArray(R.array.Quotes);

		setContentView(R.layout.main);

		// Get a reference to the QuotesFragment
		detailsFragment = (QuotesFragment) getFragmentManager()
				.findFragmentById(R.id.details);
	}

	// Called when the user selects an item in the TitlesFragment
	@Override
	public void onListSelection(int index) {
		if (detailsFragment.getShownIndex() != index) {

			// Tell the QuoteFragment to show the quote string at position index
			detailsFragment.showQuoteAtIndex(index);
		}
	}


}

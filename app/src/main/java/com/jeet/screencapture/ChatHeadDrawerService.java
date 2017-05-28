package com.jeet.screencapture;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.os.IBinder;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class ChatHeadDrawerService extends Service {

	private WindowManager mWindowManager;
	private View mChatHead;
	private ImageView mChatHeadImageView;

	private LinearLayout mLayout;
	private static int screenWidth;
	private static int screenHeight;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}


	@Override
	public void onCreate() {
		super.onCreate();

//		Typeface tf = Typeface.createFromAsset(getAssets(), "Roboto-Light.ttf");

		mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
		Display display = mWindowManager.getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		screenWidth = size.x;
		screenHeight = size.y;

		LayoutInflater inflater = LayoutInflater.from(this);
		mChatHead = inflater.inflate(R.layout.chathead_view, null);
		mChatHeadImageView = (ImageView) mChatHead
				.findViewById(R.id.chathead_imageview);

		mLayout = (LinearLayout) mChatHead
				.findViewById(R.id.chathead_linearlayout);

//		mChatHeadTextView.setTypeface(tf);

		final WindowManager.LayoutParams parameters = new WindowManager.LayoutParams(
				WindowManager.LayoutParams.WRAP_CONTENT, // Width
				WindowManager.LayoutParams.WRAP_CONTENT, // Height
				WindowManager.LayoutParams.TYPE_PHONE, // Type
				WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, // Flag
				PixelFormat.TRANSLUCENT // Format
		);

		parameters.x = 50;
		parameters.y = 50;
		parameters.gravity = Gravity.BOTTOM | Gravity.RIGHT;

		// Drag support!
        mChatHeadImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplication(), "Clicked!",
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });
		/*
		final View actionB = mChatHead.findViewById(R.id.action_b);
		FloatingActionButton actionC = new FloatingActionButton(getBaseContext());
		actionC.setTitle("Hide/Show Action above");
		actionC.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				actionB.setVisibility(actionB.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
			}
		});

		final FloatingActionsMenu menuMultipleActions = (FloatingActionsMenu) mChatHead.findViewById(R.id.multiple_actions);
		menuMultipleActions.addButton(actionC);

		final FloatingActionButton removeAction = (FloatingActionButton) mChatHead.findViewById(R.id.button_remove);
		removeAction.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				((FloatingActionsMenu) mChatHead.findViewById(R.id.multiple_actions_down)).removeButton(removeAction);
			}
		});

		ShapeDrawable drawable = new ShapeDrawable(new OvalShape());
		drawable.getPaint().setColor(getResources().getColor(R.color.white));
		((FloatingActionButton) mChatHead.findViewById(R.id.setter_drawable)).setIconDrawable(drawable);

		final FloatingActionButton actionA = (FloatingActionButton) mChatHead.findViewById(R.id.action_a);
		actionA.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				actionA.setTitle("Action A clicked");
			}
		});

		// Test that FAMs containing FABs with visibility GONE do not cause crashes
		mChatHead.findViewById(R.id.button_gone).setVisibility(View.GONE);

		final FloatingActionButton actionEnable = (FloatingActionButton) mChatHead.findViewById(R.id.action_enable);
		actionEnable.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				menuMultipleActions.setEnabled(!menuMultipleActions.isEnabled());
			}
		});

		mChatHeadImageView.setOnTouchListener(new OnTouchListener() {

			int initialX, initialY;
			float initialTouchX, initialTouchY;



			@Override
			public boolean onTouch(View v, MotionEvent event) {

				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					initialX = parameters.x;
					initialY = parameters.y;
					initialTouchX = event.getRawX();
					initialTouchY = event.getRawY();
					Toast.makeText(getApplication(),
							"Drag it to here to remove!", Toast.LENGTH_SHORT)
							.show();
					return true;

				case MotionEvent.ACTION_MOVE:

					parameters.x = initialX
							+ (int) (event.getRawX() - initialTouchX);
					parameters.y = initialY
							+ (int) (event.getRawY() - initialTouchY);
					mWindowManager.updateViewLayout(mChatHead, parameters);
					return true;

				case MotionEvent.ACTION_UP:

					if (parameters.y > screenHeight * 0.6) {
						mChatHead.setVisibility(View.GONE);
						Toast.makeText(getApplication(), "Removed!",
								Toast.LENGTH_SHORT).show();
						stopSelf();
					}

					if (parameters.x < screenWidth / 2) {
						mLayout.removeAllViews();
						mLayout.addView(mChatHeadImageView);


					} else { // Set textView to left of image
						mLayout.removeAllViews();

						mLayout.addView(mChatHeadImageView);

					}
					return true;


				default:
					return false;
				}
			}

		});
		*/

		mWindowManager.addView(mChatHead, parameters);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (mChatHead != null)
			mWindowManager.removeView(mChatHead);
	}
}

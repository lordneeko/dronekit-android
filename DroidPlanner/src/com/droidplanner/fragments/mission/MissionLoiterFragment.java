package com.droidplanner.fragments.mission;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.droidplanner.R;
import com.droidplanner.drone.variables.mission.waypoints.LoiterInfinite;
import com.droidplanner.widgets.SeekBarWithText.SeekBarWithText;
import com.droidplanner.widgets.SeekBarWithText.SeekBarWithText.OnTextSeekBarChangedListner;

public class DialogMissionLoiter extends DialogMission implements
		OnTextSeekBarChangedListner, OnCheckedChangeListener{

	private SeekBarWithText loiterRadiusSeekBar;
	private CheckBox loiterCCW;
	private SeekBarWithText yawSeekBar;
	private LoiterInfinite item;
	
	@Override
	protected int getResource() {
		return R.layout.dialog_mission_loiter;
	}
	
	protected View buildView() {
		super.buildView();	
		item =  (LoiterInfinite) wp;

		loiterCCW = (CheckBox) view.findViewById(R.string.loiter_ccw);
		if (item.getRadius()< 0) {
			loiterCCW.setChecked(true);
		} else {
			loiterCCW.setChecked(false);
		}
		loiterCCW.setOnCheckedChangeListener(this);


		loiterRadiusSeekBar = (SeekBarWithText) view
				.findViewById(R.id.loiterRadius);
		loiterRadiusSeekBar .setOnChangedListner(this);
		loiterRadiusSeekBar.setAbsValue(item.getRadius());

		yawSeekBar = (SeekBarWithText) view
				.findViewById(R.id.waypointAngle);
		yawSeekBar.setValue(item.getAngle());
		yawSeekBar.setOnChangedListner(this);



		return view;
	}
	
	

	@Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		item.setRadius(loiterRadiusSeekBar.getValue());
		if (loiterCCW.isChecked()) {
			item.setRadius(item.getRadius()*-1.0);
		}
    }
	
	@Override
	public void onSeekBarChanged() {
		item.setRadius(loiterRadiusSeekBar.getValue());
		if (loiterCCW.isChecked()) {
			item.setRadius(item.getRadius()*-1.0);
		}
		item.setAngle(yawSeekBar.getValue());
	}
}
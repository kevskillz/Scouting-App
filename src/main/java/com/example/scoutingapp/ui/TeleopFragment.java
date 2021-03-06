package com.example.scoutingapp.ui;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;

import com.example.scoutingapp.MainActivity;
import com.example.scoutingapp.QR;
import com.example.scoutingapp.R;
import com.example.scoutingapp.databinding.TeleopBinding;

public class TeleopFragment  extends Fragment {
    private TeleopBinding binding;

    private CheckBox cTraversalClimb;
    private CheckBox cHighClimb;
    private CheckBox cMidClimb;
    private CheckBox cLowClimb;
    private CheckBox cNoClimb;
    private boolean climbing = false;

    private final Handler handler = new Handler();





    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = TeleopBinding.inflate(inflater, container, false);

        binding.textHighMissedValueT.setText(""+QR.currEntry.highMissedTeleop);
        binding.textLowValueT.setText(""+QR.currEntry.lowScoredTeleop);
        binding.textHighValueT.setText(""+QR.currEntry.highScoredTeleop);
        binding.textLowMissedValueT.setText(""+QR.currEntry.lowMissedTeleop);
        binding.textViewTimer.setText("" + QR.currEntry.getStoredClimbTime());


        binding.textUserInfoT.setText(""+"Name: " + QR.currEntry.scoutName + "\nScouting: " +
                QR.currEntry.teamScouting + "\nColor: " +
                (QR.currEntry.teamColor == 'R' ? "Red" : "Blue") +
                "\nMatch: " + QR.currEntry.matchNum);

        Button bQuit = binding.buttonQuitT;
        bQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.navController.navigate(new NavDirections() {
                    @Override
                    public int getActionId() {
                        return R.id.action_navigation_teleop_to_inputinformation;
                    }

                    @NonNull
                    @Override
                    public Bundle getArguments() {
                        return null;
                    }
                });


            }

        });
        Button bSubmit = binding.buttonSubmitT;
        bSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QR.addCurrMatchEntry();
                MainActivity.navController.navigate(new NavDirections() {
                    @Override
                    public int getActionId() {
                        return R.id.action_navigation_teleop_to_qrdata;
                    }

                    @NonNull
                    @Override
                    public Bundle getArguments() {
                        return null;
                    }
                });

            }

        });
        Button bBack = binding.buttonAutonomousT;
        bBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.navController.navigate(new NavDirections() {
                    @Override
                    public int getActionId() {
                        return R.id.action_navigation_teleop_to_autonomous;
                    }

                    @NonNull
                    @Override
                    public Bundle getArguments() {
                        return null;
                    }
                });


            }

        });
        Button bHighPlus = binding.buttonHighPlusT;
        bHighPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QR.currEntry.highScoredTeleop++;
                binding.textHighValueT.setText(""+QR.currEntry.highScoredTeleop);

            }

        });
        Button bHighMinus = binding.buttonHighMinusT;
        bHighMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QR.currEntry.highScoredTeleop--;
                QR.currEntry.highScoredTeleop = Math.max(0, QR.currEntry.highScoredTeleop);

                binding.textHighValueT.setText(""+QR.currEntry.highScoredTeleop);

            }

        });
        Button bHighMissedPlus = binding.buttonHighMissedPlusT;
        bHighMissedPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QR.currEntry.highMissedTeleop++;
                binding.textHighMissedValueT.setText(""+QR.currEntry.highMissedTeleop);
            }

        });
        Button bHighMissedMinus = binding.buttonHighMissedMinusT;
        bHighMissedMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QR.currEntry.highMissedTeleop--;
                QR.currEntry.highMissedTeleop = Math.max(0, QR.currEntry.highMissedTeleop);

                binding.textHighMissedValueT.setText(""+QR.currEntry.highMissedTeleop);

            }

        });
        Button bLowPlus = binding.buttonLowPlusT;
        bLowPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QR.currEntry.lowScoredTeleop++;
                binding.textLowValueT.setText(""+QR.currEntry.lowScoredTeleop);

            }

        });
        Button bLowMinus = binding.buttonLowMinusT;
        bLowMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QR.currEntry.lowScoredTeleop--;
                QR.currEntry.lowScoredTeleop = Math.max(0, QR.currEntry.lowScoredTeleop);

                binding.textLowValueT.setText(""+QR.currEntry.lowScoredTeleop);

            }

        });
        Button bLowMissedPlus = binding.buttonLowMissedPlusT;
        bLowMissedPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QR.currEntry.lowMissedTeleop++;
                binding.textLowMissedValueT.setText(""+QR.currEntry.lowMissedTeleop);

            }

        });
        Button bLowMissedMinus = binding.buttonLowMissedMinusT;
        bLowMissedMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QR.currEntry.lowMissedTeleop--;
                QR.currEntry.lowMissedTeleop = Math.max(0, QR.currEntry.lowMissedTeleop);

                binding.textLowMissedValueT.setText(""+QR.currEntry.lowMissedTeleop);

            }

        });

        Button bStartTimer = binding.buttonStartClimb;
        bStartTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (!climbing) {

                    QR.currEntry.startClimb();
                    climbing = true;
                    binding.buttonStartClimb.setText("Stop Climb");
                    handler.post(new Runnable() {

                        @Override
                        public void run() {
                            if (climbing) {

                                binding.textViewTimer.setText("" + QR.currEntry.currClimbTimeDeltaFormatted());
                            }
                            handler.postDelayed(this, 1);
                        }
                    });
                }
                else {
                    QR.currEntry.endClimb();
                    climbing = false;
                    binding.buttonStartClimb.setText("Start Climb");

                }
            }

        });



        cNoClimb = binding.checkBoxNoClimb;
        cNoClimb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QR.currEntry.climbLevel = 0;
                onlyOneChecked(QR.currEntry.climbLevel);

            }


        });
        cLowClimb = binding.checkBoxLow;
        cLowClimb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QR.currEntry.climbLevel = 1;
                onlyOneChecked(QR.currEntry.climbLevel);

            }


        });
        cMidClimb = binding.checkBoxMid;
        cMidClimb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QR.currEntry.climbLevel = 2;
                onlyOneChecked(QR.currEntry.climbLevel);

            }


        });
        cHighClimb = binding.checkBoxHighBar;
        cHighClimb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QR.currEntry.climbLevel = 3;
                onlyOneChecked(QR.currEntry.climbLevel);

            }


        });
        cTraversalClimb = binding.checkBoxTraversal;
        cTraversalClimb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QR.currEntry.climbLevel = 4;
                onlyOneChecked(QR.currEntry.climbLevel);
            }


        });

        onlyOneChecked(QR.currEntry.climbLevel);

        return binding.getRoot();
    }

    void onlyOneChecked(int recent) {
        if (recent == 0) {
            cLowClimb.setChecked(false);
            cMidClimb.setChecked(false);
            cHighClimb.setChecked(false);
            cTraversalClimb.setChecked(false);
            cNoClimb.setChecked(true);
        }
        else if (recent == 1) {
            cNoClimb.setChecked(false);
            cMidClimb.setChecked(false);
            cHighClimb.setChecked(false);
            cTraversalClimb.setChecked(false);
            cLowClimb.setChecked(true);

        }
        else if (recent == 2) {
            cLowClimb.setChecked(false);
            cNoClimb.setChecked(false);
            cHighClimb.setChecked(false);
            cTraversalClimb.setChecked(false);
            cMidClimb.setChecked(true);
        }
        else if (recent == 3) {
            cLowClimb.setChecked(false);
            cMidClimb.setChecked(false);
            cNoClimb.setChecked(false);
            cTraversalClimb.setChecked(false);
            cHighClimb.setChecked(true);
        }
        else if (recent == 4) {
            cLowClimb.setChecked(false);
            cMidClimb.setChecked(false);
            cHighClimb.setChecked(false);
            cNoClimb.setChecked(false);
            cTraversalClimb.setChecked(true);
        }
    }

}

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.interfaces.Gyro;

public final class Custom extends TimedRobot{

    public Double absGyroAngle (Gyro gyro){

        final Double gyroAngle = gyro.getAngle();
        final Double numberOfRotaitions;
        final Double numberOfRotaitionsUnrounded;
        final Double absAngle;

        if (Math.round(gyroAngle) - gyroAngle > 1){

            numberOfRotaitionsUnrounded = (gyroAngle - 0.5) / 360;
            numberOfRotaitions = (double) Math.round(numberOfRotaitionsUnrounded);
        } else {

            numberOfRotaitionsUnrounded = gyroAngle / 360;
            numberOfRotaitions = (double) Math.round(numberOfRotaitionsUnrounded);
        }

        absAngle = gyroAngle / numberOfRotaitions;

        return absAngle;        
    }

    public Double getTargetDistance (Double Hight_of_Target, Double Hight_of_camera, double ty){
        Double absTargetHight = Hight_of_Target - Hight_of_camera;

        Double targetDistance = absTargetHight / Math.abs(ty);

        return targetDistance;
    }


    public Double driftFixTwoEncoderTalonSRX(TalonSRX Left_motor_controller, TalonSRX Right_motor_controller){

         Double driftFix;
         Double skewAmount = Math.abs(Left_motor_controller.getSelectedSensorPosition()) - Math.abs(Right_motor_controller.getSelectedSensorPosition());

         if (Math.abs(skewAmount) > 1000){
             if (skewAmount > 0){
                 driftFix = -0.3;
             }else{
                 driftFix = 0.3;
             }
         } else {
             driftFix = 0.0;
         }

        return driftFix;
    }

    public Double driftFixFourEncoderTalonSRX(TalonSRX Front_left_motor_controller, TalonSRX Front_right_motor_controller, TalonSRX Back_left_motor_controller, TalonSRX Back_right_motor_controller){

        Double rightAverage = (Front_right_motor_controller.getSelectedSensorPosition() + Back_right_motor_controller.getSelectedSensorPosition()) / 2;
        Double leftAverage = (Front_left_motor_controller.getSelectedSensorPosition() + Back_left_motor_controller.getSelectedSensorPosition()) / 2;

        Double skewAmount = Math.abs(leftAverage) - Math.abs(rightAverage);
        Double driftFix;

        if (Math.abs(skewAmount) > 1000){
            if (skewAmount > 0){
                driftFix = -0.3;
            } else {
                driftFix = 0.3;
            }
        } else {
            driftFix = 0.0;
        }

        return driftFix;

    }
}
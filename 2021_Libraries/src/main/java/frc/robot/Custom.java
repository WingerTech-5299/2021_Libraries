package frc.robot;

public final class Custom{

    public Double getTargetDistance (Double Hight_of_Target, Double Hight_of_camera, double ty){
        Double absTargetHight = Hight_of_Target - Hight_of_camera;

        Double targetDistance = absTargetHight / Math.abs(ty);

        return targetDistance;
    }

}
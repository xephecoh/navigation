package sample.Controllers;

public class GreatCircleCoursesCalculator {

    public static double calculateStartCourseDegree(double GCCourseDegStart, double RD1Deg, double LongStart, double LongFin) {
        if ((Math.abs(RD1Deg) < 180) && LongFin > LongStart && GCCourseDegStart > 0) {
            GCCourseDegStart = GCCourseDegStart * 1;
        } else if ((Math.abs(RD1Deg) < 180) && LongFin > LongStart && GCCourseDegStart < 0) {
            GCCourseDegStart = GCCourseDegStart + 180;
        } else if ((Math.abs(RD1Deg) > 180) && LongFin > LongStart && (GCCourseDegStart > 0) && GCCourseDegStart < 180) {
            GCCourseDegStart = GCCourseDegStart + 180;
        } else if ((Math.abs(RD1Deg) > 180) && LongFin > LongStart && GCCourseDegStart < 360 && GCCourseDegStart > 180) {
            GCCourseDegStart = GCCourseDegStart - 180;
        } else if (((Math.abs(RD1Deg) < 180) && (LongFin < LongStart)) && GCCourseDegStart > 0) {
            GCCourseDegStart = GCCourseDegStart + 180;
        } else if (((Math.abs(RD1Deg) < 180) && (LongFin < LongStart)) && GCCourseDegStart < 0) {
            GCCourseDegStart = GCCourseDegStart + 360;
        } else if (((Math.abs(RD1Deg) > 180) && (LongFin < LongStart)) && GCCourseDegStart > 0 && GCCourseDegStart < 180) {
            GCCourseDegStart = GCCourseDegStart * 1;
        } else if (((Math.abs(RD1Deg) > 180) && (LongFin < LongStart)) && GCCourseDegStart > 180 && GCCourseDegStart < 360) {
            GCCourseDegStart = GCCourseDegStart - 180;
        } else if (((Math.abs(RD1Deg) > 180) && (LongFin < LongStart)) && GCCourseDegStart < 0 && GCCourseDegStart > -180) {
            GCCourseDegStart = GCCourseDegStart + 180;
        } else if (((Math.abs(RD1Deg) > 180) && (LongFin < LongStart)) && GCCourseDegStart < -180 && GCCourseDegStart > -360) {
            GCCourseDegStart = GCCourseDegStart + 540;
        } else if (GCCourseDegStart < 0) {
            GCCourseDegStart = 360 + GCCourseDegStart;
        }
        return GCCourseDegStart;
    }

    public static double calculateFinalCourseDegree(double greatCircleCourseDegreesFinal, double RD1Deg, double longStart, double longFin) {
        if ((Math.abs(RD1Deg) < 180) && longFin > longStart && greatCircleCourseDegreesFinal > 0) {
            greatCircleCourseDegreesFinal = greatCircleCourseDegreesFinal * 1;
        } else if ((Math.abs(RD1Deg) < 180) && longFin > longStart && greatCircleCourseDegreesFinal < 0) {
            greatCircleCourseDegreesFinal = greatCircleCourseDegreesFinal + 180;
        } else if ((Math.abs(RD1Deg) > 180) && longFin > longStart && (greatCircleCourseDegreesFinal > 0) && 180 > greatCircleCourseDegreesFinal) {
            greatCircleCourseDegreesFinal = greatCircleCourseDegreesFinal + 180;
        } else if ((Math.abs(RD1Deg) > 180) && longFin > longStart && greatCircleCourseDegreesFinal < 360 && greatCircleCourseDegreesFinal > 180) {
            greatCircleCourseDegreesFinal = greatCircleCourseDegreesFinal - 180;
        } else if (((Math.abs(RD1Deg) < 180) && (longFin < longStart)) && greatCircleCourseDegreesFinal > 0) {
            greatCircleCourseDegreesFinal = greatCircleCourseDegreesFinal + 180;
        } else if (((Math.abs(RD1Deg) < 180) && (longFin < longStart)) && greatCircleCourseDegreesFinal < 0) {
            greatCircleCourseDegreesFinal = greatCircleCourseDegreesFinal + 360;
        } else if (((Math.abs(RD1Deg) > 180) && (longFin < longStart)) && greatCircleCourseDegreesFinal > 0 && greatCircleCourseDegreesFinal < 180) {
            greatCircleCourseDegreesFinal = greatCircleCourseDegreesFinal*1;
        } else if (((Math.abs(RD1Deg) > 180) && (longFin < longStart)) && greatCircleCourseDegreesFinal > 180 && greatCircleCourseDegreesFinal < 360) {
            greatCircleCourseDegreesFinal = greatCircleCourseDegreesFinal - 180;
        } else if (((Math.abs(RD1Deg) > 180) && (longFin < longStart)) && greatCircleCourseDegreesFinal < 0 && greatCircleCourseDegreesFinal > -180) {
            greatCircleCourseDegreesFinal = greatCircleCourseDegreesFinal + 180;
        } else if (((Math.abs(RD1Deg) > 180) && (longFin < longStart)) && greatCircleCourseDegreesFinal < -180 && greatCircleCourseDegreesFinal > -360) {
            greatCircleCourseDegreesFinal = greatCircleCourseDegreesFinal + 540;
        } else if (greatCircleCourseDegreesFinal < 0) {
            greatCircleCourseDegreesFinal = 360 + greatCircleCourseDegreesFinal;
        }
        return greatCircleCourseDegreesFinal;
    }
}



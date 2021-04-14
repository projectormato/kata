package kata.ex01;

import kata.ex01.model.Driver;
import kata.ex01.model.HighwayDrive;

import java.time.LocalDateTime;

import static kata.ex01.model.RouteType.RURAL;
import static kata.ex01.model.RouteType.URBAN;
import static kata.ex01.model.VehicleFamily.STANDARD;

public class HighwayDriveMother {

    public static HighwayDrive createHolidayHighwayDriveTestData() {
        HighwayDrive drive = new HighwayDrive();
        drive.setEnteredAt(LocalDateTime.of(2021, 1, 1, 6, 0));
        drive.setExitedAt(LocalDateTime.of(2021, 1, 1, 7, 0));
        drive.setDriver(driver(5));
        drive.setVehicleFamily(STANDARD);
        drive.setRouteType(RURAL);
        return drive;
    }

    private static Driver driver(int usingCount) {
        Driver driver = new Driver();
        driver.setCountPerMonth(usingCount);
        return driver;
    }

    public static HighwayDrive createUrbanHighwayDriveTestData() {
        HighwayDrive drive = new HighwayDrive();
        drive.setEnteredAt(LocalDateTime.of(2016, 10, 10, 6, 0));
        drive.setExitedAt(LocalDateTime.of(2021, 10, 10, 7, 0));
        drive.setDriver(driver(5));
        drive.setVehicleFamily(STANDARD);
        drive.setRouteType(URBAN);
        return drive;
    }

    public static HighwayDrive createNotHolidayHighwayDriveTestData(int enteredAtHour, int usingCount) {
        HighwayDrive drive = new HighwayDrive();
        drive.setEnteredAt(LocalDateTime.of(2016, 3, 31, enteredAtHour, 0));
        drive.setExitedAt(LocalDateTime.of(2016, 3, 31, enteredAtHour + 1, 0));
        drive.setDriver(driver(usingCount));
        drive.setVehicleFamily(STANDARD);
        drive.setRouteType(RURAL);
        return drive;
    }
}

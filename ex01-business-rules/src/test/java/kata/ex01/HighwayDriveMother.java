package kata.ex01;

import kata.ex01.model.Driver;
import kata.ex01.model.HighwayDrive;
import kata.ex01.model.RouteType;
import kata.ex01.model.VehicleFamily;

import java.time.LocalDateTime;

import static kata.ex01.model.RouteType.RURAL;
import static kata.ex01.model.RouteType.URBAN;
import static kata.ex01.model.VehicleFamily.STANDARD;

public class HighwayDriveMother {

    public static HighwayDrive createHolidayHighwayDriveTestData(RouteType routeType) {
        HighwayDrive drive = new HighwayDrive();
        drive.setEnteredAt(LocalDateTime.of(2021, 1, 1, 6, 0));
        drive.setExitedAt(LocalDateTime.of(2021, 1, 1, 7, 0));
        drive.setDriver(driver(5));
        drive.setVehicleFamily(STANDARD);
        drive.setRouteType(routeType);
        return drive;
    }

    public static HighwayDrive createHolidayHighwayDriveTestData() {
        return createHolidayHighwayDriveTestData(RURAL);
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

    public static HighwayDrive createSaturdayHighwayDriveTestData() {
        HighwayDrive drive = new HighwayDrive();
        drive.setEnteredAt(LocalDateTime.of(2021, 4, 17, 23, 0));
        drive.setExitedAt(LocalDateTime.of(2021, 4, 18, 6, 30));
        drive.setDriver(driver(10));
        drive.setVehicleFamily(STANDARD);
        drive.setRouteType(RURAL);
        return drive;
    }

    public static HighwayDrive createSundayHighwayDriveTestData() {
        HighwayDrive drive = new HighwayDrive();
        drive.setEnteredAt(LocalDateTime.of(2021, 4, 17, 23, 0));
        drive.setExitedAt(LocalDateTime.of(2021, 4, 18, 6, 30));
        drive.setDriver(driver(10));
        drive.setVehicleFamily(STANDARD);
        drive.setRouteType(RURAL);
        return drive;
    }
}

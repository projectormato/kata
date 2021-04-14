package kata.ex01;

import kata.ex01.model.Driver;
import kata.ex01.model.HighwayDrive;
import org.junit.jupiter.api.*;

import java.time.LocalDateTime;
import java.util.Arrays;

import static kata.ex01.model.RouteType.RURAL;
import static kata.ex01.model.RouteType.URBAN;
import static kata.ex01.model.VehicleFamily.STANDARD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author kawasima
 */
public class DiscountServiceTest {
    DiscountService discountService;

    @BeforeEach
    void setUp() {
        discountService = new DiscountServiceImpl();
    }

    @Test
    void 平日朝夕割引() {
        Arrays.asList(
                getHighwayDriveTestCase(createUrbanHighwayDriveTestData(), 0, "都心部の時割引なし"),
                getHighwayDriveTestCase(createHolidayHighwayDriveTestData(), 0, "休日の時割引なし"),
                getHighwayDriveTestCase(createNotHolidayHighwayDriveTestData(5, 5), 0, "平日朝5時のとき割引なし"),
                getHighwayDriveTestCase(createNotHolidayHighwayDriveTestData(6, 5), 30, "平日朝6時かつ地方部かつ当月の利用回数が5回のとき30%割引"),
                getHighwayDriveTestCase(createNotHolidayHighwayDriveTestData(9, 5), 30, "平日朝9時かつ地方部かつ当月の利用回数が5回のとき30%割引"),
                getHighwayDriveTestCase(createNotHolidayHighwayDriveTestData(10, 5), 0, "平日朝10時のとき割引なし"),
                getHighwayDriveTestCase(createNotHolidayHighwayDriveTestData(17, 5), 30, "平日夕方17時かつ地方部かつ当月の利用回数が5回のとき30%割引"),
                getHighwayDriveTestCase(createNotHolidayHighwayDriveTestData(20, 5), 30, "平日夕方20時かつ地方部かつ当月の利用回数が5回のとき30%割引"),
                getHighwayDriveTestCase(createNotHolidayHighwayDriveTestData(21, 5), 0, "平日夕方21時のとき割引なし"),
                getHighwayDriveTestCase(createNotHolidayHighwayDriveTestData(22, 5), 0, "平日夕方22時のとき割引なし"),
                getHighwayDriveTestCase(createNotHolidayHighwayDriveTestData(6, 1), 0, "平日朝6時かつ地方部かつ当月の利用回数が1回のとき割引なし"),
                getHighwayDriveTestCase(createNotHolidayHighwayDriveTestData(6, 5), 30, "平日朝6時かつ地方部かつ当月の利用回数が9回のとき30%割引"),
                getHighwayDriveTestCase(createNotHolidayHighwayDriveTestData(6, 10), 50, "平日朝6時かつ地方部かつ当月の利用回数が10回のとき50%割引")
                ).forEach(
                testCase -> assertEquals(
                        testCase.getExpected(),
                        discountService.calc(testCase.getHighwayDrive()),
                        testCase.getCaseName()
                )
        );
    }

    private HighwayDrive createHolidayHighwayDriveTestData() {
        HighwayDrive drive = new HighwayDrive();
        drive.setEnteredAt(LocalDateTime.of(2021, 1, 1, 6, 0));
        drive.setExitedAt(LocalDateTime.of(2021, 1, 1, 7, 0));
        drive.setDriver(driver(5));
        drive.setVehicleFamily(STANDARD);
        drive.setRouteType(RURAL);
        return drive;
    }

    private HighwayDrive createUrbanHighwayDriveTestData() {
        HighwayDrive drive = new HighwayDrive();
        drive.setEnteredAt(LocalDateTime.of(2016, 10, 10, 6, 0));
        drive.setExitedAt(LocalDateTime.of(2021, 10, 10, 7, 0));
        drive.setDriver(driver(5));
        drive.setVehicleFamily(STANDARD);
        drive.setRouteType(URBAN);
        return drive;
    }

    private highwayDriveTestCase getHighwayDriveTestCase(HighwayDrive highwayDrive, long expected, String caseName) {
        highwayDriveTestCase highwayDriveTestCase = new highwayDriveTestCase();
        highwayDriveTestCase.setHighwayDrive(highwayDrive);
        highwayDriveTestCase.setExpected(expected);
        highwayDriveTestCase.setCaseName(caseName);
        return highwayDriveTestCase;
    }

    private HighwayDrive createNotHolidayHighwayDriveTestData(int enteredAtHour, int usingCount) {
        HighwayDrive drive = new HighwayDrive();
        drive.setEnteredAt(LocalDateTime.of(2016, 3, 31, enteredAtHour, 0));
        drive.setExitedAt(LocalDateTime.of(2016, 3, 31, enteredAtHour + 1, 0));
        drive.setDriver(driver(usingCount));
        drive.setVehicleFamily(STANDARD);
        drive.setRouteType(RURAL);
        return drive;
    }

    @Disabled
    @Test
    public void test休日朝夕は休日割が適用される() {
        HighwayDrive drive = new HighwayDrive();
        drive.setEnteredAt(LocalDateTime.of(2016, 4, 1, 23, 0));
        drive.setExitedAt(LocalDateTime.of(2016, 4, 2, 6, 30));
        drive.setDriver(driver(10));
        drive.setVehicleFamily(STANDARD);
        drive.setRouteType(RURAL);

        assertThat(discountService.calc(drive)).isEqualTo(30);
    }

    private Driver driver(int usingCount) {
        Driver driver = new Driver();
        driver.setCountPerMonth(usingCount);
        return driver;
    }


    private class highwayDriveTestCase {
        private HighwayDrive highwayDrive;
        private long expected;
        private String caseName;

        public HighwayDrive getHighwayDrive() {
            return highwayDrive;
        }

        public void setHighwayDrive(HighwayDrive highwayDrive) {
            this.highwayDrive = highwayDrive;
        }

        public long getExpected() {
            return expected;
        }

        public void setExpected(long expected) {
            this.expected = expected;
        }

        public String getCaseName() {
            return caseName;
        }

        public void setCaseName(String caseName) {
            this.caseName = caseName;
        }
    }

}

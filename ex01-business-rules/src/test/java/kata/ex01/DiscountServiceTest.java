package kata.ex01;

import kata.ex01.model.Driver;
import kata.ex01.model.HighwayDrive;
import kata.ex01.model.RouteType;
import kata.ex01.model.VehicleFamily;
import org.junit.jupiter.api.*;

import java.time.LocalDateTime;
import java.util.Arrays;

import static kata.ex01.model.RouteType.RURAL;
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
                createHighwayDriveTestCase(HighwayDriveMother.createUrbanHighwayDriveTestData(), 0, "都心部の時割引なし"),
                createHighwayDriveTestCase(HighwayDriveMother.createNotHolidayHighwayDriveTestData(5, 5), 0, "平日朝5時のとき割引なし"),
                createHighwayDriveTestCase(HighwayDriveMother.createNotHolidayHighwayDriveTestData(6, 5), 30, "平日朝6時かつ地方部かつ当月の利用回数が5回のとき30%割引"),
                createHighwayDriveTestCase(HighwayDriveMother.createNotHolidayHighwayDriveTestData(9, 5), 30, "平日朝9時かつ地方部かつ当月の利用回数が5回のとき30%割引"),
                createHighwayDriveTestCase(HighwayDriveMother.createNotHolidayHighwayDriveTestData(10, 5), 0, "平日朝10時のとき割引なし"),
                createHighwayDriveTestCase(HighwayDriveMother.createNotHolidayHighwayDriveTestData(17, 5), 30, "平日夕方17時かつ地方部かつ当月の利用回数が5回のとき30%割引"),
                createHighwayDriveTestCase(HighwayDriveMother.createNotHolidayHighwayDriveTestData(20, 5), 30, "平日夕方20時かつ地方部かつ当月の利用回数が5回のとき30%割引"),
                createHighwayDriveTestCase(HighwayDriveMother.createNotHolidayHighwayDriveTestData(21, 5), 0, "平日夕方21時のとき割引なし"),
                createHighwayDriveTestCase(HighwayDriveMother.createNotHolidayHighwayDriveTestData(22, 5), 0, "平日夕方22時のとき割引なし"),
                createHighwayDriveTestCase(HighwayDriveMother.createNotHolidayHighwayDriveTestData(6, 1), 0, "平日朝6時かつ地方部かつ当月の利用回数が1回のとき割引なし"),
                createHighwayDriveTestCase(HighwayDriveMother.createNotHolidayHighwayDriveTestData(6, 5), 30, "平日朝6時かつ地方部かつ当月の利用回数が9回のとき30%割引"),
                createHighwayDriveTestCase(HighwayDriveMother.createNotHolidayHighwayDriveTestData(6, 10), 50, "平日朝6時かつ地方部かつ当月の利用回数が10回のとき50%割引")
        ).forEach(
                testCase -> assertEquals(
                        testCase.getExpected(),
                        discountService.calc(testCase.getHighwayDrive()),
                        testCase.getCaseName()
                )
        );
    }

    @Nested
    class 休日割引 {
        @Test
        public void 普通車_土曜_地方部_30割引() {
            HighwayDrive drive = HighwayDriveMother.createSaturdayHighwayDriveTestData();
            assertThat(discountService.calc(drive)).isEqualTo(30);
        }

        @Test
        public void 普通車_日曜_地方部30割引() {
            HighwayDrive drive = HighwayDriveMother.createSundayHighwayDriveTestData();
            assertThat(discountService.calc(drive)).isEqualTo(30);
        }

        @Test
        public void 普通車_祝日_地方部30割引() {
            HighwayDrive drive = HighwayDriveMother.createHolidayHighwayDriveTestData();
            assertThat(discountService.calc(drive)).isEqualTo(30);
        }

        @Test
        public void 普通車_祝日_都市部割引なし() {
            HighwayDrive drive = HighwayDriveMother.createHolidayHighwayDriveTestData(RouteType.URBAN);
            assertThat(discountService.calc(drive)).isEqualTo(0);
        }
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

    private highwayDriveTestCase createHighwayDriveTestCase(HighwayDrive highwayDrive, long expected, String caseName) {
        highwayDriveTestCase highwayDriveTestCase = new highwayDriveTestCase();
        highwayDriveTestCase.setHighwayDrive(highwayDrive);
        highwayDriveTestCase.setExpected(expected);
        highwayDriveTestCase.setCaseName(caseName);
        return highwayDriveTestCase;
    }

}

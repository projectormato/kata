package kata.ex01;

import kata.ex01.model.HighwayDrive;
import kata.ex01.model.RouteType;
import kata.ex01.model.VehicleFamily;
import kata.ex01.util.HolidayUtils;

/**
 * @author kawasima
 */
public class DiscountServiceImpl implements DiscountService {
    @Override
    public long calc(HighwayDrive drive) {
        if (HolidayUtils.isHoliday(drive.getEnteredAt().toLocalDate())) {
            if (drive.getVehicleFamily() == VehicleFamily.STANDARD && drive.getRouteType() == RouteType.RURAL) {
                return 30;
            }
            return 0;
        }
        if (drive.getRouteType() == RouteType.URBAN) {
            return 0;
        }
        int enteredHour = drive.getEnteredAt().getHour();
        if (enteredHour <= 5 || (enteredHour >= 10 && enteredHour < 17) || enteredHour >= 21) {
            return 0;
        }
        if (drive.getDriver().getCountPerMonth() < 5) {
            return 0;
        }
        if (drive.getDriver().getCountPerMonth() >= 10) {
            return 50;
        }
        return 30;
    }
}

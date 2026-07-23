public class DesignParkingSystem {
    int[] spots;

    public DesignParkingSystem(int big, int medium, int small) {
        spots = new int[] { 0, big, medium, small };
    }

    public boolean addCar(int carType) {
        if (spots[carType] > 0) {
            spots[carType]--;
            return true;
        }
        return false;
    }
}
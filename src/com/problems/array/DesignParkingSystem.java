package com.problems.array;

/*
 * Problem link:
 * https://leetcode.com/problems/design-parking-system/description/
 *
 * Solution link:
 * https://www.youtube.com/watch?v=d5zCHesOrSk
 *
 * https://neetcode.io/solutions/design-parking-system
 */
public class DesignParkingSystem {
    public static void main(String[] args) {
        type1();
    }

    private static void type1() {
        ParkingSystem parkingSystem = new ParkingSystem(1, 1, 0);
        parkingSystem.addCar(1);
        parkingSystem.addCar(2);
        parkingSystem.addCar(3);
        parkingSystem.addCar(1);
    }

    static class ParkingSystem {
        int[] parking;

        public ParkingSystem(int big, int medium, int small) {
            this.parking = new int[]{big, medium, small};
        }

        public boolean addCar(int carType) {
            if (parking[carType - 1] == 0) return false;
            parking[carType - 1]--;
            return true;
        }
    }

}

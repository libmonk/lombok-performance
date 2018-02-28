/*
 * Copyright (c) 2018, www.libmonk.com
 * All rights reserved.
 *
 * BSD 3-Clause License
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 *
 *  Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 *  Neither the name of the copyright holder nor the names of its
 *   contributors may be used to endorse or promote products derived from
 *   this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 */

package com.libmonk.lombok.performance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class BuilderTest {

    public static void main(String[] args) throws InterruptedException {
        List<Vehicle> vehicleList = new ArrayList<Vehicle>();
        Thread.sleep(10000); // wait for profiler to be configured
        long startTime = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            vehicleList.add(new Vehicle((short)4, "Audi", "A8", 3000, 100000,
                    (short)5, FuelType.PETROL, "RR1111", "Red", TransmissionType.AUTO,
                    VehicleType.FOUR_WHEELER, UsageType.PERSONAL));

//            vehicleList.add(Vehicle.builder().wheels((short)4).brand("Audi").model("A8").engineCapacity(3000)
//                    .price(100000).seatingCapacity((short)5).fuelType(FuelType.PETROL).regNo("RR1111")
//                    .color("Red").transmissionType(TransmissionType.AUTO).vehicleType(VehicleType.FOUR_WHEELER)
//                    .usageType(UsageType.PERSONAL).build());
        }
        double timeTaken = (System.nanoTime()-startTime)/1000.0;
        System.out.println("Time taken in microseconds: "+timeTaken);
        System.gc();
        Thread.sleep(5000);
    }

    @Data
    @AllArgsConstructor
    @Builder
    static class Vehicle {
        private short wheels;
        private String brand;
        private String model;
        private int engineCapacity;
        private int price;
        private short seatingCapacity;
        private FuelType fuelType;
        private String regNo;
        private String color;
        private TransmissionType transmissionType;
        private VehicleType vehicleType;
        private UsageType usageType;
    }

    enum FuelType {
        PETROL, DIESEL, ELECTRIC
    }

    enum TransmissionType {
        AUTO, MANUAL
    }

    enum VehicleType {
        TWO_WHEELER, FOUR_WHEELER, TRUCK, BUS
    }

    enum UsageType {
        PERSONAL, COMMERCIAL
    }

}

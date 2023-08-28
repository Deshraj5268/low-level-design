package elevator.service;

import elevator.model.Floor;

import java.util.PriorityQueue;

public class ElevatorStrategyAlgorithm {

   private PriorityQueue<Floor> minHeapForUpDir;
   private PriorityQueue<Floor> maxHeapForDownDir;

    public ElevatorStrategyAlgorithm(){
        this.minHeapForUpDir = new PriorityQueue<>((x,y)->(x.getId()-y.getId()));
        this.maxHeapForDownDir = new PriorityQueue<>((x,y)->(y.getId()-x.getId()));
    }

    public void pendingFloorForUpDirection(Floor floor){
        minHeapForUpDir.add(floor);
    }

    public Floor servingForUpDirection(){
        return minHeapForUpDir.isEmpty() ? new Floor(-1): minHeapForUpDir.poll();
    }

    public void pendingFloorForDownDirection(Floor floor){
        maxHeapForDownDir.add(floor);
    }

    public Floor servingForDownDirection(){
        return minHeapForUpDir.isEmpty() ? new Floor(-1): minHeapForUpDir.poll();
    }
}

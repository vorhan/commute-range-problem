package com.test.commuterangeproblem.service.searchcities;

import com.test.commuterangeproblem.model.GraphNode;
import com.test.commuterangeproblem.model.GraphEdge;
import com.test.commuterangeproblem.repository.RouteRepository;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

@Service
public class SearchCitiesServiceDijkstraImpl implements SearchCitiesService {

    @Autowired
    RouteRepository routeRepository;

    public List<Integer> findReachableCityIds(int startCityId, double timeLimit) {

        final List<Integer> reachableCityIds = new LinkedList<>();
        final TreeSet<GraphNode> heap = new TreeSet<>();
        final List<Integer> passedCityIds = new LinkedList<>();

        reachableCityIds.add(startCityId);
        heap.add(new GraphNode(startCityId, 0));

        while (!heap.isEmpty()) {
            val shortestCityWay = heap.pollFirst();
            int currentCityId = shortestCityWay.getCityId();
            passedCityIds.add(currentCityId);

            for (GraphEdge graphEdge : routeRepository.findRoutesToNeighbourCities(currentCityId, passedCityIds, timeLimit-shortestCityWay.getTimeDistance())) {
                int destinationCityId = graphEdge.getCityIdFrom() != currentCityId
                    ? graphEdge.getCityIdFrom()
                    : graphEdge.getCityIdTo();

                double newTimeDist = shortestCityWay.getTimeDistance() + graphEdge.getTimeDistance();

                if (heap.stream().noneMatch(graphNode -> graphNode.getCityId() == destinationCityId)) {
                    heap.add(new GraphNode(destinationCityId, newTimeDist));
                    reachableCityIds.add(destinationCityId);
                } else {
                    GraphNode p = heap.stream().filter(c -> c.getCityId() == destinationCityId).findFirst().get();
                    if (newTimeDist < p.getTimeDistance()) {
                        heap.remove(p);
                        heap.add(new GraphNode(p.getCityId(), newTimeDist));
                    }
                }
            }
        }
        return reachableCityIds;
    }
}
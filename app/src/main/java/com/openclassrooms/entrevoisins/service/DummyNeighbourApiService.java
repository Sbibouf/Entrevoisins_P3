package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();
    private List<Neighbour> FavNeighbours = new ArrayList<>();


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }
    @Override
    public List<Neighbour> getFavNeighbours() {

        for(Neighbour neighbour : neighbours) {

            if(neighbour.isFav() && !FavNeighbours.contains(neighbour)){
                FavNeighbours.add(neighbour);
            }
            else if (!neighbour.isFav() && FavNeighbours.contains(neighbour) ) {
                FavNeighbours.remove(neighbour);
            }
        }
        return FavNeighbours;

        }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }
    public void deleteFavNeighbour(Neighbour neighbour) {FavNeighbours.remove(neighbour);}

    /**
     * {@inheritDoc}
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }

    @Override
    public void createFavNeighbour(Neighbour FavNeighbour) {FavNeighbours.add(FavNeighbour);}
}

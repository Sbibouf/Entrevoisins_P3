package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.DummyNeighbourGenerator;
import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    @Override
    public List<Neighbour> getFavNeighbours() {

        List<Neighbour> FavNeighbours = new ArrayList<>();
        for(Neighbour neighbour : neighbours) {

            if(neighbour.isFav()==true && !FavNeighbours.contains(neighbour)){
                FavNeighbours.add(neighbour);
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
    public void deleteFavNeighbour(Neighbour neighbour) {neighbours.get(neighbours.indexOf(neighbour)).setFav(false);}

    /**
     * {@inheritDoc}
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }

    @Override
    public void createFavNeighbour(Neighbour neighbour) {neighbours.get(neighbours.indexOf(neighbour)).setFav(true);}
}

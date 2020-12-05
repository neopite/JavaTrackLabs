package lab3.com.company.neophite.controller;

import lab3.com.company.neophite.model.entity.TrainTrip;
import lab3.com.company.neophite.model.service.TrainTripService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ServletRailway")
public class ServletRailway extends HttpServlet {
    private TrainTripService trainTripService;

    @Override
    public void init() throws ServletException {
        this.trainTripService = new TrainTripService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String startCity = request.getParameter("stCity");
        String endCity = request.getParameter("endCity");
        List<TrainTrip> listOfTrainTripsByStations =
                trainTripService.getTrainTripsBetweenTwoStations(startCity , endCity);
        
    }
}

package lk.ijse.gdse66.javaee.api;

import jakarta.annotation.Resource;
import jakarta.json.*;
import lk.ijse.gdse66.javaee.bo.BOFactory;
import lk.ijse.gdse66.javaee.bo.custom.CustomerBO;
import lk.ijse.gdse66.javaee.dto.CustomerDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author : Kavithma Thushal
 * @project : JavaEE-POS
 * @since : 6:56 PM - 1/15/2024
 **/
@WebServlet(urlPatterns = "/api/v1/customer")
public class CustomerServlet extends HttpServlet {
    private final CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);

    @Resource(name = "java:comp/env/jdbc/pool")
    DataSource pool;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String address = req.getParameter("address");
        double salary = Double.parseDouble(req.getParameter("salary"));

        CustomerDTO customerDTO = new CustomerDTO(id, name, address, salary);
        try (Connection connection = pool.getConnection()) {
            boolean customerSaved = customerBO.saveCustomer(customerDTO, connection);

            JsonObjectBuilder response = Json.createObjectBuilder();
            if (customerSaved) {
                response.add("status", "200 OK");
                response.add("message", "Saved Successfully...!");
                resp.setStatus(HttpServletResponse.SC_OK);
            } else {
                response.add("status", "Error 500");
                response.add("message", "Failed to save the customer");
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
            resp.getWriter().print(response.build());

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            JsonObjectBuilder response = Json.createObjectBuilder();
            response.add("status", "Error 500");
            response.add("message", e.getMessage());
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().print(response.build());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        JsonReader jsonReader = Json.createReader(req.getReader());
        JsonObject jsonObject = jsonReader.readObject();

        String id = jsonObject.getString("id");
        String name = jsonObject.getString("name");
        String address = jsonObject.getString("address");
        double salary = Double.parseDouble(jsonObject.getString("salary"));

        CustomerDTO customerDTO = new CustomerDTO(id, name, address, salary);
        try (Connection connection = pool.getConnection()) {
            boolean customerUpdated = customerBO.updateCustomer(customerDTO, connection);

            JsonObjectBuilder response = Json.createObjectBuilder();
            if (customerUpdated) {
                response.add("status", "200 OK");
                response.add("message", "Updated Successfully...!");
                resp.setStatus(HttpServletResponse.SC_OK);
            } else {
                response.add("status", "Error 500");
                response.add("message", "Failed to update the customer");
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
            resp.getWriter().print(response.build());

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            JsonObjectBuilder response = Json.createObjectBuilder();
            response.add("status", "Error 500");
            response.add("message", e.getMessage());
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().print(response.build());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        JsonReader jsonReader = Json.createReader(req.getReader());
        JsonObject jsonObject = jsonReader.readObject();
        String id = jsonObject.getString("id");

        try (Connection connection = pool.getConnection()) {
            boolean customerDeleted = customerBO.deleteCustomer(id, connection);

            JsonObjectBuilder response = Json.createObjectBuilder();
            if (customerDeleted) {
                response.add("status", "200 OK");
                response.add("message", "Deleted Successfully...!");
                resp.setStatus(HttpServletResponse.SC_OK);
            } else {
                response.add("status", "Error 500");
                response.add("message", "Failed to delete the customer");
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
            resp.getWriter().print(response.build());

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            JsonObjectBuilder response = Json.createObjectBuilder();
            response.add("status", "Error 500");
            response.add("message", e.getMessage());
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().print(response.build());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String option = req.getParameter("option");

        switch (option) {
            case "customerCount":
                try (Connection connection = pool.getConnection()) {

                    JsonObjectBuilder response = Json.createObjectBuilder();
                    resp.getWriter().print(response.build());

                } catch (SQLException e) {
                    e.printStackTrace();
                    JsonObjectBuilder response = Json.createObjectBuilder();
                    response.add("status", "Error 500");
                    response.add("message", e.getMessage());
                    resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    resp.getWriter().print(response.build());
                }
                break;

            case "searchCusId":
                try (Connection connection = pool.getConnection()) {
                    ArrayList<CustomerDTO> customerArrayList = customerBO.customerSearchId(id, connection);

                    JsonObjectBuilder response = Json.createObjectBuilder();
                    if (!customerArrayList.isEmpty()) {
                        for (CustomerDTO customerDetails : customerArrayList) {
                            response.add("id", customerDetails.getId());
                            response.add("name", customerDetails.getName());
                            response.add("address", customerDetails.getAddress());
                            response.add("salary", customerDetails.getSalary());
                        }

                    } else {
                        response.add("status", "Error 500");
                        response.add("message", "Failed to search the customer");
                        resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    }
                    resp.getWriter().print(response.build());

                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                    JsonObjectBuilder response = Json.createObjectBuilder();
                    response.add("status", "Error 500");
                    response.add("message", e.getMessage());
                    resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    resp.getWriter().print(response.build());
                }
                break;

            case "loadAllCustomers":
                try (Connection connection = pool.getConnection()) {
                    ArrayList<CustomerDTO> customerArrayList = customerBO.getAllCustomers(connection);
                    JsonArrayBuilder jsonAllCustomersArrayBuilder = Json.createArrayBuilder();

                    JsonObjectBuilder response = Json.createObjectBuilder();
                    for (CustomerDTO customerDTO : customerArrayList) {
                        response.add("id", customerDTO.getId());
                        response.add("name", customerDTO.getName());
                        response.add("address", customerDTO.getAddress());
                        response.add("salary", customerDTO.getSalary());
                        jsonAllCustomersArrayBuilder.add(response.build());
                    }

                    response.add("status", "200 OK");
                    response.add("message", "Loaded Successfully...!");
                    response.add("data", jsonAllCustomersArrayBuilder.build());
                    resp.setStatus(HttpServletResponse.SC_OK);
                    resp.getWriter().print(response.build());

                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                    JsonObjectBuilder response = Json.createObjectBuilder();
                    response.add("status", "Error 500");
                    response.add("message", e.getMessage());
                    resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    resp.getWriter().print(response.build());
                }
                break;

            case "generateCusId":
                try (Connection connection = pool.getConnection()) {
                    String cusId = customerBO.generateNewCustomerID(connection);

                    JsonObjectBuilder response = Json.createObjectBuilder();
                    response.add("id", cusId);
                    resp.getWriter().print(response.build());

                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                    JsonObjectBuilder response = Json.createObjectBuilder();
                    response.add("status", "Error 500");
                    response.add("message", e.getMessage());
                    resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    resp.getWriter().print(response.build());
                }
                break;
        }
    }
}
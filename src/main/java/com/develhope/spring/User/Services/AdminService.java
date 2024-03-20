package com.develhope.spring.User.Services;

import com.develhope.spring.User.DTO.CustomerDTO;
import com.develhope.spring.User.DTO.SalesmanDTO;
import com.develhope.spring.User.Entities.Customer;
import com.develhope.spring.User.Entities.Salesman;
import com.develhope.spring.User.Repositories.AdminRepository;
import com.develhope.spring.User.Repositories.CustomerRepository;
import com.develhope.spring.User.Repositories.SalesmanRepository;
import com.develhope.spring.Vehicle.Entities.Enums.*;
import com.develhope.spring.Vehicle.Entities.Vehicle;
import com.develhope.spring.Vehicle.Repositories.VehicleRepository;
import com.develhope.spring.Purchase.Entities.Purchase;
import com.develhope.spring.Purchase.Repositories.PurchaseRepository;
import com.develhope.spring.Purchase.Entities.DTO.AdminPurchaseCreationDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class AdminService {

    @Autowired
    AdminRepository adminRepository;
    @Autowired
    SalesmanRepository salesmanRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private PurchaseRepository purchaseRepository;

    private SalesmanDTO getSalesmanDTO(Salesman salesman){

        SalesmanDTO salesmanDTO = new SalesmanDTO();

        salesmanDTO.setId(salesman.getId());
        salesmanDTO.setFirstName(salesman.getFirstName());
        salesmanDTO.setLastName(salesman.getLastName());
        salesmanDTO.setPhone(salesman.getPhone());
        salesmanDTO.setAddress(salesman.getAddress());
        salesmanDTO.setEmail(salesman.getEmail());
        salesmanDTO.setSalesNumber(salesman.getSalesNumber());

        return salesmanDTO;
    }

    private CustomerDTO getCustomerDTO(Customer customer){

        CustomerDTO customerDTO = new CustomerDTO();

        customerDTO.setId(customer.getId());
        customerDTO.setFirstName(customer.getFirstName());
        customerDTO.setLastName(customer.getLastName());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setPhone(customer.getPhone());
        customerDTO.setAddress(customer.getAddress());
        customerDTO.setCreditCard(customer.getCreditCard());
        customerDTO.setTaxId(customer.getTaxId());

        return customerDTO;
    }

    //get lista salesman
    public List<SalesmanDTO> getSalesmenList(){

        List<Salesman> salesmen = salesmanRepository.findAll();
        List<SalesmanDTO> salesmanDTOS = new ArrayList<>();

        for (Salesman salesman : salesmen){

            SalesmanDTO salesmanDTO = getSalesmanDTO(salesman);

            salesmanDTOS.add(salesmanDTO);
        }

        return salesmanDTOS;
    }

    //cancella account salesman
    public Boolean deleteASalesman(Long id){

        Salesman deletedSalesman = salesmanRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Salesman not found by id " + id));

        salesmanRepository.deleteById(id);

        return true;
    }

    //modifica account salesman
    public Salesman modifySalesman(Long id, Salesman salesman)
    {

        Salesman salesmanUpdated = salesmanRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Salesman not found by id " + id));

        if (salesman.getFirstName() != null && !salesman.getFirstName().isEmpty()){

            salesmanUpdated.setFirstName(salesman.getFirstName());
        }
        if (salesman.getLastName() != null && !salesman.getLastName().isEmpty()){

            salesmanUpdated.setLastName(salesman.getLastName());
        }
        if (salesman.getAddress() != null && !salesman.getAddress().isEmpty()){

            salesmanUpdated.setAddress(salesman.getAddress());
        }
        if (salesman.getPhone() != null && !salesman.getPhone().isEmpty()){

            salesmanUpdated.setPhone(salesman.getPhone());
        }

        salesmanUpdated.setFirstName(salesman.getFirstName());
        salesmanUpdated.setLastName(salesman.getLastName());
        salesmanUpdated.setAddress(salesman.getAddress());
        salesmanUpdated.setPhone(salesman.getPhone());

        return salesmanRepository.save(salesmanUpdated);
    }

    //get lista customer
    public List<CustomerDTO> getCustomersList(){

        List<Customer> customers = customerRepository.findAll();
        List<CustomerDTO> customerDTOs = new ArrayList<>();

        for (Customer customer : customers){

            CustomerDTO customerDTO = getCustomerDTO(customer);

            customerDTOs.add(customerDTO);
        }

        return customerDTOs;
    }

    //cancella account customer
    public Boolean deleteACustomer(Long id){

        Customer deletedCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found by id " + id));

        customerRepository.deleteById(id);

        return true;
    }

    //modifica account customer
    public Customer modifyCustomer(Long id, Customer customer)
    {

        Customer customerUpdated = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Salesman not found by id " + id));

        if (customer.getFirstName() != null && !customer.getFirstName().isEmpty()){

            customerUpdated.setFirstName(customer.getFirstName());
        }
        if (customer.getLastName() != null && !customer.getLastName().isEmpty()){

            customerUpdated.setLastName(customer.getLastName());
        }
        if (customer.getAddress() != null && !customer.getAddress().isEmpty()){

            customerUpdated.setAddress(customer.getAddress());
        }
        if (customer.getPhone() != null && !customer.getPhone().isEmpty()){

            customerUpdated.setPhone(customer.getPhone());
        }

        customerUpdated.setFirstName(customer.getFirstName());
        customerUpdated.setLastName(customer.getLastName());
        customerUpdated.setAddress(customer.getAddress());
        customerUpdated.setPhone(customer.getPhone());

        return customerRepository.save(customerUpdated);
    }

    //aggiungi veicolo
    public Vehicle newVehicle(Vehicle vehicle){

        return vehicleRepository.save(vehicle);
    }

    //cancella veicolo
    public Boolean deleteVehicle(Long id){
        Vehicle vehicleDeleted = vehicleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle id " + id + " to delete not found"));

        if(vehicleDeleted.getStatusTypeEnum().equals(StatusTypeEnum.PURCHASABLE) || vehicleDeleted.getStatusTypeEnum().equals(StatusTypeEnum.ORDERABLE)){
            vehicleRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }

    //modifica veicolo
        public Vehicle updateVehicle(Long id, Vehicle vehicle){
        Vehicle vehicleUpdated = vehicleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle id " + id + " to update not found"));

        if(vehicle.getVehiclesTypeEnum() != null && Arrays.asList(VehiclesTypeEnum.values())
                .contains(vehicle.getVehiclesTypeEnum())){
            vehicleUpdated.setVehiclesTypeEnum(vehicle.getVehiclesTypeEnum());
        }
        if(vehicle.getBrand() != null && !vehicle.getBrand().isEmpty()){
            vehicleUpdated.setBrand(vehicle.getBrand());
        }
        if(vehicle.getModel() != null && !vehicle.getModel().isEmpty()){
            vehicleUpdated.setModel(vehicle.getModel());
        }
        if(vehicle.getColour() != null && !vehicle.getColour().isEmpty()){
            vehicleUpdated.setColour(vehicle.getColour());
        }
        if(vehicle.getCubiCapacity() != null){
            vehicleUpdated.setCubiCapacity(vehicle.getCubiCapacity());
        }
        if(vehicle.getHP() != null){
            vehicleUpdated.setHP(vehicle.getHP());
        }
        if(vehicle.getKW() != null){
            vehicleUpdated.setKW(vehicle.getKW());
        }
        if(vehicle.getRegistrationYear() != null){
            vehicleUpdated.setRegistrationYear(vehicle.getRegistrationYear());
        }
        if(vehicle.getFuelTypeEnum() != null && Arrays.asList(FuelTypeEnum.values())
                .contains(vehicle.getFuelTypeEnum())){
            vehicleUpdated.setFuelTypeEnum(vehicle.getFuelTypeEnum());
        }
        if(vehicle.getPrice() != null){
            vehicleUpdated.setPrice(vehicle.getPrice());
        }
        //nullable column
        vehicleUpdated.setTradeDiscount(vehicle.getTradeDiscount());

        if(vehicle.getNewVehicle() != null){
            vehicleUpdated.setNewVehicle(vehicle.getNewVehicle());
        }
        //nullable column
        vehicleUpdated.setMileage(vehicle.getMileage());

        if(vehicle.getAgeLimit() != null){
            vehicleUpdated.setAgeLimit(vehicle.getAgeLimit());
        }
        if(vehicle.getStatusTypeEnum() != null && Arrays.asList(StatusTypeEnum.values())
                .contains(vehicle.getStatusTypeEnum())){
            vehicleUpdated.setStatusTypeEnum(vehicle.getStatusTypeEnum());
        }
        //nullable column
        vehicleUpdated.setCurrentLocation(vehicle.getCurrentLocation());

        if(vehicle.getAvailableRental() != null){
            vehicleUpdated.setAvailableRental(vehicle.getAvailableRental());
        }
        if(vehicle.getEmissionTypeEnum() != null && Arrays.asList(EmissionTypeEnum.values())
                .contains(vehicle.getEmissionTypeEnum())){
            vehicleUpdated.setEmissionTypeEnum(vehicle.getEmissionTypeEnum());
        }
        if(vehicle.getPassengerNumber() != null){
            vehicleUpdated.setPassengerNumber(vehicle.getPassengerNumber());
        }
        //nullable column
        vehicleUpdated.setWindShield(vehicle.getWindShield());

        vehicleUpdated.setTailBag(vehicle.getTailBag());

        vehicleUpdated.setPassengerBackrest(vehicle.getPassengerBackrest());

        vehicleUpdated.setTransmissionTypeEnum(vehicle.getTransmissionTypeEnum());

        vehicleUpdated.setCarTypeEnum(vehicle.getCarTypeEnum());

        vehicleUpdated.setTractionTypeEnum(vehicle.getTractionTypeEnum());

        vehicleUpdated.setOptionFullTraction(vehicle.getOptionFullTraction());

        vehicleUpdated.setDoors(vehicle.getDoors());

        vehicleUpdated.setCentralizedClosing(vehicle.getCentralizedClosing());

        vehicleUpdated.setAirConditioning(vehicle.getAirConditioning());

        vehicleUpdated.setBluetooth(vehicle.getBluetooth());

        vehicleUpdated.setSatNav(vehicle.getSatNav());

        vehicleUpdated.setElectricRoof(vehicle.getElectricRoof());

        vehicleUpdated.setParkAssist(vehicle.getParkAssist());

        vehicleUpdated.setSpareTire(vehicle.getSpareTire());

        vehicleUpdated.setAntiTheft(vehicle.getAntiTheft());

        vehicleUpdated.setPassengerTransport(vehicle.getPassengerTransport());

        vehicleUpdated.setWindowedBackDoor(vehicle.getWindowedBackDoor());

        vehicleUpdated.setSlideSideDoor(vehicle.getSlideSideDoor());

        vehicleUpdated.setAntiCollisionSystem(vehicle.getAntiCollisionSystem());

        return vehicleRepository.save(vehicleUpdated);
    }

    //cambia STATUS di un veicolo
    public Vehicle updateStatusType(Long id, StatusTypeEnum statusType){
        Vehicle vehicleUpdated = vehicleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle id " + id + " to update not found"));

        if(statusType != null && Arrays.asList(StatusTypeEnum.values()).contains(statusType)) {
            vehicleUpdated.setStatusTypeEnum(statusType);
            return vehicleRepository.save(vehicleUpdated);
        }else{
            throw new EntityNotFoundException("Status type of vehicle not correct");
        }
    }

    //modifica ACQUISTO per un CUSTOMER
    public List<Purchase> updatePurchaseById(Long idCustomer, Long idPurchase, Purchase purchaseUpdated){
        if(idCustomer != null && idPurchase != null && customerRepository.findById(idCustomer).isPresent()
                && purchaseRepository.findById(idPurchase).isPresent()) {

            List<Purchase> purchaseListByCustomer = purchaseRepository.purchasesByCustomer(idCustomer);

            for (Purchase purchase : purchaseListByCustomer){
                if (Objects.equals(purchase.getId(), idPurchase)){
                    if(purchaseUpdated.getAdvancePayment() != null) {
                        purchase.setAdvancePayment(purchaseUpdated.getAdvancePayment());
                    }
                    if(purchaseUpdated.getIsPaid() != null){
                        purchase.setIsPaid(purchaseUpdated.getIsPaid());
                    }
                    if(purchaseUpdated.getOrderStatusEnum() != null){
                        purchase.setOrderStatusEnum(purchaseUpdated.getOrderStatusEnum());
                    }
                    if(purchaseUpdated.getSalesman() != null){
                        purchase.setSalesman(purchaseUpdated.getSalesman());
                    }
                }
                purchaseRepository.save(purchase);
            }
            return purchaseListByCustomer;
        }else{
            throw new RuntimeException("Something went wrong");
        }
    }

    //cancella ACQUISTO per un CUSTOMER
    public Boolean deletePurchaseById(Long idCustomer, Long idPurchase){
        if(idCustomer != null && idPurchase != null && customerRepository.findById(idCustomer).isPresent()
                && purchaseRepository.findById(idPurchase).isPresent()) {
            Iterator<Purchase> iterator = purchaseRepository.purchasesByCustomer(idCustomer).listIterator();
            while (iterator.hasNext()){
                Purchase purchase = iterator.next();
                if(idPurchase.equals(purchase.getId())){
                    iterator.remove();
                    break;
                }
            }
            return true;
        }else{
            throw new RuntimeException("Something went wrong");
        }
    }


    //ottieni VEICOLI filtrandoli per STATUSTYPE
    public List<Vehicle> vehiclesByStatusType(StatusTypeEnum statusType){

        if(statusType != null && Arrays.asList(StatusTypeEnum.values()).contains(statusType)) {
            List<Vehicle> vehiclesByStatusTypeEnum = new ArrayList<>();
            for(Vehicle vehicle : vehicleRepository.findAll()){
                if(vehicle.getStatusTypeEnum() == statusType){
                    vehiclesByStatusTypeEnum.add(vehicle);
                }
            }
            return vehiclesByStatusTypeEnum;
        }else {
            throw new RuntimeException("Vehicle status " + statusType + " not found");
        }
    }
    public Purchase createNewPurchase(AdminPurchaseCreationDTO dto){
        Vehicle vehicle = vehicleRepository.findById(dto.getIdVehicle())
                .orElseThrow(() -> new NoSuchElementException("Veicolo con id " + dto.getIdVehicle() + " non trovato"));

        if(vehicle.getStatusTypeEnum().equals(StatusTypeEnum.PURCHASABLE)){

            Purchase purchase = new Purchase();
            purchase.setVehicle(vehicle);
            purchase.setSalesman(dto.getSalesman());
            purchase.setCustomer(dto.getCustomer());
            purchase.setAdvancePayment(vehicle.getPrice());
            purchase.setIsPaid(false);
            purchase.setVehicleStatusEnum(dto.getVehicleStatus());

            vehicle.setStatusTypeEnum(StatusTypeEnum.SOLD);
            vehicleRepository.save(vehicle);

            return purchaseRepository.save(purchase);
        } else {
            throw new IllegalStateException("Il veicolo non è acquistabile.");
        }
    }
}


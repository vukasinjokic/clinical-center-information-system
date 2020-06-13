package com.example.demo.service;


import com.example.demo.Repository.*;
import com.example.demo.dto.ClinicDTO;
import com.example.demo.model.*;
import com.example.demo.useful_beans.ChartAppointment;
import com.example.demo.model.Clinic;
import com.example.demo.model.ClinicAdmin;
import com.example.demo.model.Nurse;
import com.example.demo.model.Prescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.Calendar;

@Service
public class ClinicService {
    private static Set<Clinic> clinics;

    @Autowired
    private ClinicRepository clinicRepository;
    @Autowired
    private ClinicAdminRepository clinicAdminRepository;

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private CodeBookRepository codeBookRepository;
    @Autowired
    private ExaminationTypeRepository examinationTypeRepository;
    @Autowired
    private PriceListItemRepository priceListItemRepository;
    @Autowired
    private PriceListRepository priceListRepository;


    public Clinic findById(Integer id) {
        return clinicRepository.findById(id).orElse(null);
    }

    public List<Clinic> getAllClinics() {
        return clinicRepository.findAll();
    }

    public boolean gradeClinic(Clinic clinic, Integer patientId, float newGrade) {
        Rating clinicRating = clinic.getRating();
        clinicRating.setGrade(patientId, newGrade);
        clinicRating = ratingRepository.save(clinicRating);
        return clinicRating != null;
    }

    public Clinic addClinic(ClinicDTO clinicDTO) throws ParseException{
        Clinic clinic = new Clinic(clinicDTO.getName(), clinicDTO.getDescription(), clinicDTO.getAddress());
        clinicRepository.save(clinic);
        return clinic;
    }
    public PriceList getPriceList(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //ClinicAdmin admin = clinicAdminRepository.findByEmailAndFetchClinicEagerly(user.getEmail());
        Optional<ClinicAdmin> ad = clinicAdminRepository.findById(user.getId());
        ClinicAdmin admin = ad.get();
        if(admin != null){
            Clinic clinic = admin.getClinic();
            return clinic.getPriceList();
        }
        return null;
    }

    public float getClinicRating(String email){
        ClinicAdmin clinicAdmin = clinicAdminRepository.findByEmailAndFetchClinicEagerly(email);
        return clinicAdmin.getClinic().getRating().getAverageGrade();
    }

    public List<ChartAppointment> makeChartAppointment(String period,String time, String admin_email){
        List<ChartAppointment> chart;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ClinicAdmin clinicAdmin = clinicAdminRepository.findByEmailAndFetchClinicEagerly(admin_email);

        if(period.equals("DAILY")){
            chart = makeDailyChart(date, clinicAdmin.getClinic());
        }else if(period.equals("MONTHLY")){
            chart = makeMonthlyChart(date, clinicAdmin.getClinic());
        }else{
            chart = makeYearlyChart(date, clinicAdmin.getClinic());
        }
        return chart;
    }

    public List<ChartAppointment> makeDailyChart(Date nowDate, Clinic clinic){
        List<ChartAppointment> dailyChart = new ArrayList<ChartAppointment>();
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar2 = GregorianCalendar.getInstance();
        HashMap<String, Integer> mapa = new HashMap<String, Integer>();

        for(Appointment app : clinic.getAppointments()){
            if(fmt.format(nowDate).equals(fmt.format(app.getTime()))){
                calendar2.setTime(app.getTime());
                if(mapa.containsKey(calendar2.get(Calendar.HOUR_OF_DAY)+"h")){
                    mapa.put(calendar2.get(Calendar.HOUR_OF_DAY) + "h", mapa.get(calendar2.get(Calendar.HOUR_OF_DAY)+"h") + 1);
                }else
                    mapa.put(calendar2.get(Calendar.HOUR_OF_DAY) + "h", 1);
            }
        }
        this.fillMap(mapa, "h", 25);
        this.fillChartList(mapa, dailyChart);
        Collections.sort(dailyChart);
        return dailyChart;
    }

    public List<ChartAppointment> makeMonthlyChart(Date nowDate, Clinic clinic){
        List<ChartAppointment> monthlyChart = new ArrayList<ChartAppointment>();
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMM");
        Calendar calendar2 = GregorianCalendar.getInstance();
        HashMap<String, Integer> mapa = new HashMap<String, Integer>();

        for(Appointment app : clinic.getAppointments()){
            if(fmt.format(nowDate).equals(fmt.format(app.getTime()))){
                calendar2.setTime(app.getTime());
                if(mapa.containsKey(calendar2.get(Calendar.DAY_OF_MONTH)+".")){
                    mapa.put(calendar2.get(Calendar.DAY_OF_MONTH) + "." , mapa.get(calendar2.get(Calendar.DAY_OF_MONTH)+".") + 1);
                }else
                    mapa.put(calendar2.get(Calendar.DAY_OF_MONTH) + ".", 1);
            }
        }
        this.fillMap(mapa, ".", 31);
        this.fillChartList(mapa,monthlyChart);

        Collections.sort(monthlyChart);
        return monthlyChart;
    }

    public List<ChartAppointment> makeYearlyChart(Date nowDate, Clinic clinic){
        List<ChartAppointment> yearlyChart = new ArrayList<ChartAppointment>();
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy");
        Calendar calendar2 = GregorianCalendar.getInstance();
        HashMap<String, Integer> mapa = new HashMap<String, Integer>();

        for(Appointment app : clinic.getAppointments()){
            if(fmt.format(nowDate).equals(fmt.format(app.getTime()))){
                calendar2.setTime(app.getTime());
                if(mapa.containsKey(calendar2.get(Calendar.MONTH )+".")){
                    mapa.put(calendar2.get(Calendar.MONTH) + "." , mapa.get(calendar2.get(Calendar.MONTH)+".") + 1);
                }else
                    mapa.put(calendar2.get(Calendar.MONTH) + ".", 1);
            }
        }
        this.fillYearMap(mapa,".", 12);
        this.fillChartList(mapa,yearlyChart);

        Collections.sort(yearlyChart);
        this.makeMonths(yearlyChart);
        return yearlyChart;

    }
    private void makeMonths(List<ChartAppointment> yearlyChart){
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sep", "Oct", "Nov", "Dec"};
            for(int i = 0; i<yearlyChart.size(); i++){
                yearlyChart.get(i).x = months[i];
            }
    }

    private void fillMap(HashMap<String, Integer> chartMap, String assign, int numberOfIteration){
        for(int i = 1; i < numberOfIteration; i++) {
            String a = i + assign;
            if (!chartMap.containsKey(a)) {
                chartMap.put(a, 0);
            }
        }
    }

    private void fillYearMap(HashMap<String, Integer> chartMap, String assign, int numberOfIteration){
        for(int i = 0; i < numberOfIteration; i++) {
            String a = i + assign;
            if (!chartMap.containsKey(a)) {
                chartMap.put(a, 0);
            }
        }
    }

    private void fillChartList(HashMap<String, Integer> chartMap, List<ChartAppointment> chartList){
        for(Map.Entry<String, Integer> entry : chartMap.entrySet()){
            ChartAppointment chart = new ChartAppointment();
            chart.x = entry.getKey();
            chart.y = entry.getValue();
            chartList.add(chart);
        }
    }

    public PriceListItem addPriceListItem(PriceListItem priceListItem){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<ClinicAdmin> ad = clinicAdminRepository.findById(user.getId());

        if(this.DoesExTypeNameExist(priceListItem.getExaminationType().getName(),ad.get()))
            return null;

        ExaminationType examinationType = examinationTypeRepository.findByName(priceListItem.getExaminationType().getName());
        priceListItem.setExaminationType(examinationType);
        if(ad.isPresent()){
            ClinicAdmin admin = clinicAdminRepository.findByEmailAndFetchClinicEagerly(user.getEmail());
            Clinic clinic = admin.getClinic();
            PriceList priceList = clinic.getPriceList();
            if(priceList.getId() == null){
                priceList = new PriceList();
            }
            priceList.getItems().add(priceListItem);
            priceList.setClinic(admin.getClinic());
            priceListRepository.save(priceList);

            return priceListItemRepository.findByExaminationTypeId(examinationType.getId()).get();
        }
        return null;
    }

    public PriceListItem updatePriceListItem(PriceListItem priceListItem){
        Optional<PriceListItem> findItem = priceListItemRepository.findById(priceListItem.getId());
        if(findItem.isPresent()){
            PriceListItem updateItem = findItem.get();
            updateItem.setPrice(priceListItem.getPrice());
           // updateItem.setExaminationType(priceListItem.getExaminationType());
            return priceListItemRepository.save(updateItem);
        }
        return null;
    }

    public Clinic updateClinic(ClinicDTO clinicDTO){
        Optional<Clinic> find_clinic = clinicRepository.findById(Integer.parseInt(clinicDTO.getId()));
        //validiraj ime
        List<Clinic> clinics = getAllClinics();
        Optional<Clinic> clinicOptional = clinics.stream().filter(clinic -> clinic.getName().equals(clinicDTO.getName())).findFirst();
        if(find_clinic.isPresent()){
            Clinic clinic = find_clinic.get();
            if(!clinicDTO.getName().equals(clinic.getName())){
                if(clinicOptional.isPresent())
                    return null;
            }
            clinic.setName(clinicDTO.getName());
            clinic.setAddress(clinicDTO.getAddress());
            clinic.setDescription(clinicDTO.getDescription());
            return clinicRepository.save(clinic);
        }
        return null;
    }

    public Clinic findByAdminEmail(String email){
        ClinicAdmin admin = clinicAdminRepository.findByEmailAndFetchClinicEagerly(email);
        return this.findById(admin.getClinic().getId());
    }

    public boolean DoesExTypeNameExist(String name, ClinicAdmin admin){
        if(admin.getClinic().getPriceList() == null)
        {
            admin.getClinic().setPriceList(new PriceList());
            return false;
        }
        List<PriceListItem> priceListItems = (List<PriceListItem>) admin.getClinic().getPriceList().getItems();
        Optional<PriceListItem> it = priceListItems.stream()
                .filter(item -> item.getExaminationType().getName().equals(name))
                .findAny();
        if(it.isPresent())
            return true;
        return false;
    }
}
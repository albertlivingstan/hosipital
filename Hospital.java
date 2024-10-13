import java.util.ArrayList;

public class Hospital {
    private ArrayList<Patient> patients;
    private ArrayList<Doctor> doctors;

    public Hospital() {
        patients = new ArrayList<>();
        doctors = new ArrayList<>();
    }

    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
    }

    public String getDetails() {
        StringBuilder details = new StringBuilder();
        details.append("Doctors List:\n");
        for (Doctor doctor : doctors) {
            details.append(doctor.toString()).append("\n");
        }
        details.append("\nPatients List:\n");
        for (Patient patient : patients) {
            details.append(patient.toString()).append("\n");
        }
        return details.toString();
    }
}

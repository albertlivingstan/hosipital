import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class HospitalManagementSystem {

    private Hospital hospital;

    public HospitalManagementSystem() {
        hospital = new Hospital();
        initUI();
    }

    private void initUI() {
        JFrame frame = new JFrame("Hospital Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

        // Create panels and layout
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Hospital Management System");
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Button to add patient
        JButton addPatientBtn = new JButton("Add Patient");
        addPatientBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        addPatientBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPatient();
            }
        });

        // Button to add doctor
        JButton addDoctorBtn = new JButton("Add Doctor");
        addDoctorBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        addDoctorBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addDoctor();
            }
        });

        // Button to show details and generate PDF
        JButton showDetailsBtn = new JButton("Show Details & Generate PDF");
        showDetailsBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        showDetailsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    showDetailsAndGeneratePDF();
                } catch (FileNotFoundException | DocumentException ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Add components to panel
        panel.add(label);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(addPatientBtn);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(addDoctorBtn);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(showDetailsBtn);

        // Add panel to frame and display
        frame.add(panel);
        frame.setVisible(true);
    }

    private void addPatient() {
        String name = JOptionPane.showInputDialog("Enter Patient's Name:");
        int age = Integer.parseInt(JOptionPane.showInputDialog("Enter Patient's Age:"));
        String disease = JOptionPane.showInputDialog("Enter Disease:");

        Patient patient = new Patient(name, age, disease);
        hospital.addPatient(patient);
        JOptionPane.showMessageDialog(null, "Patient Added Successfully!");
    }

    private void addDoctor() {
        String name = JOptionPane.showInputDialog("Enter Doctor's Name:");
        String specialization = JOptionPane.showInputDialog("Enter Specialization:");

        Doctor doctor = new Doctor(name, specialization);
        hospital.addDoctor(doctor);
        JOptionPane.showMessageDialog(null, "Doctor Added Successfully!");
    }

    private void showDetailsAndGeneratePDF() throws FileNotFoundException, DocumentException {
        String details = hospital.getDetails();
        JOptionPane.showMessageDialog(null, details);

        // Create PDF with details
        createPDF(details);
    }

    private void createPDF(String content) throws FileNotFoundException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("DoctorAppointmentConfirmation.pdf"));

        document.open();
        document.add(new Paragraph("Appointment Confirmation Letter"));
        document.add(new Paragraph("Hospital Management System"));
        document.add(new Paragraph("\nDetails:"));
        document.add(new Paragraph(content));
        document.close();

        JOptionPane.showMessageDialog(null, "PDF Generated: DoctorAppointmentConfirmation.pdf");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HospitalManagementSystem());
    }
}

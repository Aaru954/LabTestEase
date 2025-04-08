package com.example.lab;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;

public class ChatPageActivity extends AppCompatActivity {

    LinearLayout chatLayout;
    EditText userInput;
    Button sendButton;
    ScrollView scrollView;

    int step = -1;
    HashMap<String, String> userInfo = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_page);

        chatLayout = findViewById(R.id.chatLayout);
        userInput = findViewById(R.id.userInput);
        sendButton = findViewById(R.id.sendButton);
        scrollView = findViewById(R.id.scrollView);

        botReply("👋 Hello! What would you like to do?\n1. Book Appointment PDF\n2. Ask a Query\n3. Contact Us\n4. App Info");

        sendButton.setOnClickListener(v -> {
            String message = userInput.getText().toString().trim();
            if (!message.isEmpty()) {
                userMessage(message);
                userInput.setText("");
                processMessage(message);
            }
        });
    }

    private void processMessage(String message) {
        if (step == -1) {
            switch (message.trim()) {
                case "1":
                    botReply("📝 Please enter your name:");
                    step = 0;
                    break;
                case "2":
                    botReply("❓ Please type your query.");
                    step = 100;
                    break;
                case "3":
                    botReply("📞 You can contact us at: support@labtestapp.com or call 123-456-7890.");
                    break;
                case "4":
                    botReply(" Lab Test Ease is your smart companion for booking and managing pathology lab test appointments.\n\n"
                            + "🧪 Pathology lab tests involve examination of blood, urine, tissues, and other samples to diagnose diseases, monitor health, and guide treatments.\n\n"
                            + "📲 With this app, you can:\n"
                            + "• Book lab tests easily via chatbot\n"
                            + "• Get PDF reports instantly\n"
                            + "• Stay organized, paper-free and stress-free.");
                    break;
                default:
                    botReply("⚠ Please choose a valid option: 1, 2, 3 or 4.");
                    break;
            }
            return;
        }

        switch (step) {
            case 0:
                if (!isValidName(message)) {
                    botReply("❌ Please enter a valid name.");
                    return;
                }
                userInfo.put("name", message);
                botReply("📅 How old are you?");
                step++;
                break;
            case 1:
                if (!isValidAge(message)) {
                    botReply("❌ Please enter a valid age.");
                    return;
                }
                userInfo.put("age", message);
                botReply("📞 Your contact number?");
                step++;
                break;
            case 2:
                if (!isValidContact(message)) {
                    botReply("❌ Please enter a valid 10-digit mobile number.");
                    return;
                }
                userInfo.put("contact", message);
                botReply("🧪 Which lab test? (e.g., Blood Test)");
                step++;
                break;
            case 3:
                if (!isValidTestName(message)) {
                    botReply("❌ Test name should contain letters only.");
                    return;
                }
                userInfo.put("test", message);
                botReply("📅 Preferred date? (Format: YYYY-MM-DD)");
                step++;
                break;
            case 4:
                if (!isValidDate(message)) {
                    botReply("❌ Please enter a valid date in format YYYY-MM-DD.");
                    return;
                }
                userInfo.put("date", message);
                botReply("✅ Booking confirmed for " + userInfo.get("test") + " on " + userInfo.get("date") + ".");
                generateReport();
                resetConversation();
                break;
            case 100:
                handleUserQuery(message);
                botReply("❓ Anything else you’d like to ask? Type your query or enter 0 to go back to the main menu.");
                if (message.equalsIgnoreCase("0")) {
                    resetConversation();
                }
                break;

        }
    }

    private void resetConversation() {
        step = -1;
        userInfo.clear();
        botReply("Would you like to do anything else?\n1. Book Appointment PDF\n2. Ask a Query\n3. Contact Us\n4. App Info");
    }

    private void generateReport() {
        String report = "Lab Report Summary\n"
                + "Name: " + userInfo.get("name") + "\n"
                + "Age: " + userInfo.get("age") + "\n"
                + "Contact: " + userInfo.get("contact") + "\n"
                + "Test: " + userInfo.get("test") + "\n"
                + "Date: " + userInfo.get("date") + "\n"
                + "Status: Completed\n"
                + "Result: All parameters normal.";

        try {
            File pdfFile = generatePdf(report);
            botReply("📄 Your lab report is ready!");
            addPdfOpenButton(pdfFile);
        } catch (Exception e) {
            botReply("❌ Error generating PDF.");
        }
    }

    private void handleUserQuery(String query) {
        query = query.toLowerCase();

        if (query.contains("blood test")) {
            botReply("🩸 A blood test measures various components in your blood such as red and white cells, hemoglobin, and platelets.");
        } else if (query.contains("fasting")) {
            botReply("🥣 For some blood tests like glucose or lipid profile, you may need to fast for 8–12 hours.");
        } else if (query.contains("report")) {
            botReply("📄 Reports are usually available within 24 hours after the test.");
        } else if (query.contains("book") || query.contains("appointment")) {
            botReply("📝 To book an appointment, please choose option 1 from the main menu.");
        } else if (query.contains("contact")) {
            botReply("📞 You can contact us at: support@labtestapp.com or call 123-456-7890.");
        } else if (query.contains("test types") || query.contains("available tests")) {
            botReply("🧪 We offer Blood Test, Urine Test, Thyroid Test, Liver Function Test, and more.");
        } else {
            botReply("🤖 Sorry, I couldn't understand that. Please try asking about test types, reports, or appointments.");
        }
    }


    private File generatePdf(String content) throws Exception {
        File dir = new File(getExternalFilesDir(null), "pdfs");
        if (!dir.exists()) dir.mkdirs();

        File pdfFile = new File(dir, "lab_report.pdf");
        com.itextpdf.text.Document document = new com.itextpdf.text.Document();
        com.itextpdf.text.pdf.PdfWriter.getInstance(document, new FileOutputStream(pdfFile));
        document.open();



        document.add(new com.itextpdf.text.Paragraph(content));
        document.close();

        return pdfFile;
    }

    private void addPdfOpenButton(File pdfFile) {
        Button viewBtn = new Button(this);
        viewBtn.setText("📄 View Lab Report");
        viewBtn.setBackgroundResource(R.drawable.bot_bubble);
        viewBtn.setTextColor(getResources().getColor(android.R.color.black));
        viewBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, PdfViewerActivity.class);
            intent.putExtra("pdf_path", pdfFile.getAbsolutePath());
            startActivity(intent);
        });

        chatLayout.addView(viewBtn);
        scrollToBottom();
    }

    private void userMessage(String message) {
        TextView textView = new TextView(this);
        textView.setText(message);
        textView.setBackgroundResource(R.drawable.user_bubble);
        textView.setTextColor(getResources().getColor(android.R.color.white));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(50, 8, 0, 8);
        params.gravity = Gravity.END;
        textView.setLayoutParams(params);
        textView.setPadding(20, 15, 20, 15);
        chatLayout.addView(textView);
        scrollToBottom();
    }

    private void botReply(String message) {
        TextView textView = new TextView(this);
        textView.setText(message);
        textView.setBackgroundResource(R.drawable.bot_bubble);
        textView.setTextColor(getResources().getColor(android.R.color.black));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 8, 50, 8);
        params.gravity = Gravity.START;
        textView.setLayoutParams(params);
        textView.setPadding(20, 15, 20, 15);
        chatLayout.addView(textView);
        scrollToBottom();
    }

    private void scrollToBottom() {
        scrollView.post(() -> scrollView.fullScroll(View.FOCUS_DOWN));
    }

    private boolean isValidName(String name) {
        return name.matches("^[a-zA-Z\\s]{2,}$");
    }

    private boolean isValidAge(String ageStr) {
        try {
            int age = Integer.parseInt(ageStr);
            return age > 0 && age <= 120;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isValidContact(String contact) {
        return contact.matches("^[6-9]\\d{9}$");
    }

    private boolean isValidTestName(String test) {
        return test.matches("^[a-zA-Z\\s]{2,}$");
    }

    private boolean isValidDate(String date) {
        return date.matches("^\\d{4}-\\d{2}-\\d{2}$");
}
}
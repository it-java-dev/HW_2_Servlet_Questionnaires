package com.gmail;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@WebServlet(urlPatterns = "/")
public class FormServlet extends HttpServlet {
    List<List<String>> answersList = new ArrayList<>();
    List<String> questionsList = new ArrayList<>();

    @Override
    public void init() {
        questionsList.add("Which PC operating system do you mostly use?");
        answersList.add(Arrays.asList("Microsoft Windows", "Mac OS", "UNIX OS", "Linux", "Other"));
        questionsList.add("Which mobile operating system do you mostly use?");
        answersList.add(Arrays.asList("Android", "Symbian", "iOS", "BlackBerry OS", "Windows Mobile", "Other"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("questionsList", questionsList);
        req.setAttribute("answersList", answersList);
        try {
            getServletContext().getRequestDispatcher("/form.jsp").forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        List<String> answers = new ArrayList<>();
        for (int i = 0; i < questionsList.size(); i++) {
            answers.add(request.getParameter("question" + i));
        }

        saveAnswersToFile("d:/answers", answers);

        request.setAttribute("questionsList", questionsList);
        request.setAttribute("answers", answers);
        try {
            getServletContext().getRequestDispatcher("/result.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void saveAnswersToFile(String fileName, List<String> answers) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(" (dd.MM.yy hh-mm-ss)");
        File file = new File(fileName + simpleDateFormat.format(System.currentTimeMillis())+".ser");
        try (ObjectOutputStream fileOut = new ObjectOutputStream(new FileOutputStream(file))) {
            file.createNewFile();
            fileOut.writeObject(answers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
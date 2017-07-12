package service;

import data.Person;

import java.io.*;
import java.util.*;

/**
 * Created by CodeAcademy on 2017.07.11.
 */
public class PersonService {

    public static final String PATH = "C:\\Users\\CodeAcademy\\IdeaProjects\\coGUI\\src\\resource\\";

    public List<Person> sortByGender(List<Person> list, String gender) {
        List<Person> sortedList = new ArrayList<>();
        for (Person person : list) {
            if (person.getGender().equals(gender)) {
                sortedList.add(person);
            }
        }
        return sortedList;
    }

    public List<Person> getPersonData(String fileName) {
        List<Person> personModelList = null;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(PATH + fileName));
            personModelList = new ArrayList<>();
            bufferedReader.readLine();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] split = line.split(",");
                Person personModel = new Person();
                personModel.setId(Integer.parseInt(split[0]));
                personModel.setFirst_name(split[1]);
                personModel.setLast_name(split[2]);
                personModel.setEmail(split[3]);
                personModel.setGender(split[4]);
                personModel.setIpAddress(split[5]);
                personModelList.add(personModel);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return personModelList;
    }

    public List<String> getPersonEmails(List<Person> personList) {
        List<String> personEmails = new ArrayList<>();
        for (Person person : personList) {
            if (!person.getEmail().isEmpty()) {
                personEmails.add(person.getEmail());
            }
        }
        return personEmails;
    }

    public void removePersonsByGender(List<Person> personList, String gender) {
        Iterator<Person> iterator = personList.iterator();
        while (iterator.hasNext()) {
            if (!iterator.next().getGender().equals(gender)) {
                iterator.remove();
            }
        }
    }

    public Map<Integer, Person> addPersonToMap(List<Person> personList) {
        Map<Integer, Person> map = new HashMap<>();
        int i = 1;
        for (Person person : personList) {
            map.put(i, person);
            i++;
        }
        return map;
    }

    public Person getPersonDataById(int id, List<Person> list) {
        Map<Integer, Person> personMap = addPersonToMap(list);

        Person person = null;

        for (Map.Entry<Integer, Person> persons : personMap.entrySet()) {
            if (persons.getKey() == id) {
                person = personMap.get(id);
            }
        }
        return person;
    }

    public static Person deserializeObject(String fileName) {
        Person personSerializable = null;
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(PATH + fileName));
            personSerializable = (Person) objectInputStream.readObject();
            objectInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return personSerializable;
    }

    public static void writeSerializableObject(Person person, String fileName) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File(PATH + fileName)));
            objectOutputStream.writeObject(person);
            objectOutputStream.reset();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToFile(Person person, String fileName) {
        String content = person.getId() + "," + person.getFirst_name() + "," + person.getLast_name() + "," + person.getEmail() + "," + person.getGender() + "," + person.getIp_address();
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(PATH + fileName, true));
            bufferedWriter.newLine();
            bufferedWriter.write(content);
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteDataFromFile(String fileName, int id) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(PATH + fileName));

            String lineToRemove = String.valueOf(id);
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                // trim newline when comparing with lineToRemove
                String trimmedLine = currentLine.trim();
                if (trimmedLine.equals(lineToRemove)) continue;
            }

            reader.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
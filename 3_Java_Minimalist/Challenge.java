import java.util.*;

public class Challenge {

    String weightedSort(String text) {

        Set<String> wordWithSum = new HashSet<>();

        String[] arr = text.split(" ");

        for (String ss : arr) {
            wordWithSum.add(getASCIISumOfWord(ss));
        }

        ArrayList<String> arrayList = new ArrayList<>(wordWithSum);

        arrayList.sort(new Comparator<String>() {
            public int compare(String o1, String o2) {

                int charCount = 0;

                if (extractInt(o1) - extractInt(o2) == 0) {

                    if (extractASCII(o1, charCount) - extractASCII(o2, charCount) == 0) {
                        charCount++;
                    }

                    return extractASCII(o1, charCount) - extractASCII(o2, charCount);
                }

                return extractInt(o1) - extractInt(o2);
            }

            int extractASCII(String s, int c) {
                return s.charAt(c);
            }
            
            int extractInt(String s) {
                String num = s.replaceAll("\\D", "");
                return num.isEmpty() ? 0 : Integer.parseInt(num);
            }
        }.reversed());
        arrayList.remove("|0");
        return String.join("\n", arrayList);
    }

    String getASCIISumOfWord(String word) {

        int sum = 0;
        for (int i = 0; i < word.length(); i++) {
            word = word.replaceAll("[^\\sa-zA-Z0-9]", "");
            if (word.length() != 0) {
                int asciiValue = word.charAt(i);
                sum = sum + asciiValue;
            }
        }
        return word + "|" + sum;
    }
}

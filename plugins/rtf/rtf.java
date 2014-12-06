import java.io.*;
import java.lang.Object;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//\{\*?\\[^{}]+}|[{}]|\\\n?[A-Za-z]+\n?(?:-?\d+)?[ ]?

//\{\*?\\[^{}]+(?:-?\d+)[ ]?

public class rtf {
	private static String nomFichier = "map.csv";
	private static String destination = "final.txt";
	private static String CSV_SEPARATOR = ";";
	private static File fileEntree;
	private static File fileSortie;

	public static HashMap<String, String> createMapFromCSV(File f)
			throws IOException {

		// open buffer of file f
		BufferedReader br = new BufferedReader(new FileReader(f));

		// creating string hashmap
		HashMap<String, String> map = new HashMap<String, String>();

		// get line from file f
		String line = br.readLine();
		String[] splitstr;

		while (line != null) {
			// split the csv with the separator and put it in the hashmap
			splitstr = line.split(CSV_SEPARATOR, 2);
			map.put(splitstr[0], splitstr[1]);
			line = br.readLine();
		}
		return map;
	}

	public static void modifyFile(File source, File dest) {

		// Declaration et ouverture des flux
		File file = new File(nomFichier);// chemin vers le fichier
		HashMap<String, String> map = null;

		FileWriter f = null;
		try {
			// creation hashmap
			map = createMapFromCSV(file);
			BufferedReader brsource = new BufferedReader(new FileReader(source));
			f = new FileWriter(fileSortie);

			String line = brsource.readLine();
			while (line != null) {
				for (String key : map.keySet()) {
					// brsource.readLine().replaceAll(key,map.get(key));
					line = line.replaceAll(Pattern.quote(key),
							Matcher.quoteReplacement(map.get(key)));
				}

				f.write(line);
				line = brsource.readLine();

			}

			// f.write(map.get(brsource));
			f.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String cleanline(String line, String key, String value) {
		return value;

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		fileEntree = new File("buffalo96.rtf");
		fileSortie = new File(destination);
		modifyFile(fileEntree, fileSortie);
	}
}

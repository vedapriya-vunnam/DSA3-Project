TEXT SEARCH AND ANALYSIS SYSTEM

FEATURES
1. User selects the dataset source first.
2. Option 1 loads the included large predefined dataset.
3. Option 2 loads any user's .txt dataset using its file path.
4. The selected dataset is reused by every algorithm.
5. The user selects the task; the program automatically runs the matching algorithm.

ALGORITHM MAPPING
1. Particular word/pattern -> KMP
2. Spelling comparison -> Edit Distance
3. Multiple keyword search -> Aho-Corasick
4. Text indexing -> Suffix Array
5. Common-prefix analysis -> LCP Array

RUN
cd TextSearchProject
javac -d out src/*.java
java -cp out Main

USER DATASET
Use a plain .txt file. Give its full path when choosing option 2.
Example:
C:\\Users\\YourName\\Documents\\large_dataset.txt

For Suffix Array and LCP Array, the implementation analyzes the first 6000 characters of the selected dataset to keep memory/time practical for a classroom Java project. KMP, Edit Distance and Aho-Corasick process the full selected text.

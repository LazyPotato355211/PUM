data class StudentScore(val name: String, val subject: String, val score: Int)

fun main() {
    val students = listOf(
        StudentScore("Alice", "Math", 78),
        StudentScore("Bob", "Math", 45),
        StudentScore("Charlie", "Physics", 92),
        StudentScore("Dave", "Physics", 55),
        StudentScore("Eve", "Physics", 40),
        StudentScore("Frank", "CS", 60),
        StudentScore("Grace", "CS", 80),
    )

    val (passedBySubject, failed, subjectsAllPassed) = analyzeResults(students) // podzial/dekns na trzy niezalezne zmienne

    println("Zdani studenci wedlug przedmiotow: $passedBySubject")
    println("Niezdani studenci: $failed")
    println("Przedmioty, w ktorych wszyscy zdali: $subjectsAllPassed")
}

fun analyzeResults(students: List<StudentScore>): Triple<Map<String, List<StudentScore>>, List<StudentScore>, List<String>> {
    // podzial listy na dwie, partition -> Pair<List, List>
    val (passed, failed) = students.partition { it.score >= 50 }

    val passedBySubject = passed.groupBy { it.subject } //grup student by subj, when pass
    
    // pobr wszystk przed unikatowych
    val allSubjects = students.map { it.subject }.distinct()
    val failedSubjects = failed.map { it.subject }.distinct()
    val subjectsAllPassed = allSubjects.filter { it !in failedSubjects }
    
    return Triple(passedBySubject, failed, subjectsAllPassed)
}
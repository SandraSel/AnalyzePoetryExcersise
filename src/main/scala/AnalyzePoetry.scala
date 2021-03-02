import scala.io.Source

object AnalyzePoetry extends App {

  //with extends App we get access to command line arguments
  println("Command line arguments")
  args.foreach(println)

  val default_path = "src/resources/poetry_1922.txt"

  val real_path = if (args.length > 0) args(0) else default_path

  println(s"Will open files from $real_path")

  def getLinesFromFile(srcPath: String) = {
    val bufferedSource = Source.fromFile(srcPath)
    val lines = bufferedSource.getLines.toArray
    bufferedSource.close
    lines
  }

  val lines = getLinesFromFile(real_path)
  println(s"We have ${lines.length} lines in our $real_path file")

  println("*"*60)

  def getPoems(lines: Array[String], startLine: Int, endLine: Int): Array[String] = {
    val inArea = lines.slice(startLine, endLine)
    val poems = inArea.filter(line => line.startsWith("  "))
    poems
  }

  val poems = getPoems(lines, 340, 4488)
  poems.foreach(println)

  val savePoems = poems.mkString("\n")

  val relative_save_path = "src/resources/poetry_only_poems.txt"
  import java.io.{PrintWriter, File}
   val pw = new PrintWriter(new File(relative_save_path ))
  pw.write(savePoems)
  pw.close()

}

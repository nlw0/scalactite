import java.nio.charset.StandardCharsets
import java.nio.file.{Files, Paths, StandardOpenOption}

import org.pegdown._

import scala.collection.JavaConversions._

object Scalactite extends App {

  val processor = new PegDownProcessor(
    Extensions.ABBREVIATIONS |
    Extensions.DEFINITIONS |
    Extensions.FENCED_CODE_BLOCKS |
    Extensions.QUOTES |
    Extensions.SMARTS |
    Extensions.TABLES
  )

  val fileExtensionPattern = """(.*)\.md$""".r

  if (!(sys.props contains "input")) {
    System.err.println("Usage:\n" +
                       "    java -Dinput=<markdown_root> -jar scalactite-assembly-1.0.jar")
    System.exit(1)
  }
  val inputFolder = sys.props("input")

  val fileList = Files.newDirectoryStream(Paths.get(inputFolder))

  for {
    filename <- fileList map (_.toString)
    fileRoot <- fileExtensionPattern findFirstMatchIn filename
    outputFilename = s"${fileRoot group 1}.html"
  } {
    val markdown = io.Source.fromFile(filename).mkString
    val html = processor.markdownToHtml(markdown)
    println(s"Converting $filename to $outputFilename")
    write(outputFilename, html)
  }

  def write(filePath: String, contents: String) = {
    Files.write(Paths.get(filePath), contents.getBytes(StandardCharsets.UTF_8), StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE)
  }
}

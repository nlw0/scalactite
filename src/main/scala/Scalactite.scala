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

  val inputFolder = sys.props("input")

  val fileList = Files.newDirectoryStream(Paths.get(inputFolder))

  for {
    filename <- fileList map (_.toString)
    fileRoot <- fileExtensionPattern findFirstMatchIn filename
    outputFilename = s"${fileRoot group 1}.html"
  } {
    val markdown = io.Source.fromFile(filename).mkString
    val html = processor.markdownToHtml(markdown)
    write(outputFilename, html)
  }

  def write(filePath: String, contents: String) = {
    Files.write(Paths.get(filePath), contents.getBytes(StandardCharsets.UTF_8), StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE)
  }
}

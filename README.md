Scalactite
==========

Right now this is just a silly small program that uses pegdown to convert markdown files to html.

Usage:
    java -Dinput=<markdown_root> -jar scalactite-assembly-1.0.jar
    
Example:

```
nlw@kvelertak ~> java -Dinput=/home/nlw/public_html/ -jar ~/bin/scalactite-assembly-1.0.jar
Converting /home/nlw/public_html/index.md to /home/nlw/public_html/index.html
Converting /home/nlw/public_html/publications.md to /home/nlw/public_html/publications.html
Converting /home/nlw/public_html/doctorate.md to /home/nlw/public_html/doctorate.html
Converting /home/nlw/public_html/masters.md to /home/nlw/public_html/masters.html
```
    

## Future plans
Some sort of easy automation to help you keep a static webpage as a bunch of markdown files. 
# Librerías necesarias
import xlrd
import unicodecsv as csv
import sys

# Lectura del Excel con los logs
workbook = xlrd.open_workbook('./python/ficheros/' + sys.argv[1] + '.xlsx')
worksheet = workbook.sheet_by_index(0)
num_rows = worksheet.nrows - 1
curr_row = 0

with open('LogsAmpliado.csv', 'wb') as csvfile:
        spamwriter = csv.writer(csvfile, delimiter=',', quotechar='"', quoting=csv.QUOTE_ALL)
        spamwriter.writerow(["hora", "contexto", "componente", "nombre", "user id", "descripcion", "module id"])
        # Escritura en csv con las columnas ampliadas de la descripción
        while curr_row < num_rows:

                # Inicialización de los tres campos.
                a1 = ""
                a2 = ""
                a3 = "NULL"

                # Indice++
                curr_row += 1

                # Obtención del campo descripción e inserción de las palabras en un array
                description = worksheet.cell_value(rowx=curr_row, colx=4)
                words = description.split(" ")

                # Obtención de campo "Código de usuario"
                a1 = int(words[4][1:-1])

                # Obtención de campo "Descripción del evento"
                i = 5
                while i < len(words) :
                        if (words[i - 1] == "course") :
                                break
                        a2 += words[i] + " "
                        i = i + 1
                a2 = a2.rstrip()

                # Obtención del campo "Código de módulo"
                if (i < len(words) and (words[i] == "module")):
                        a3 = int(words[len(words) - 1][1:-2])
                
                # Generación de la fila con todos los campos, incluidos los ya existentes
                n1 = worksheet.cell_value(rowx=curr_row, colx=0)
                n2 = worksheet.cell_value(rowx=curr_row, colx=1)
                if ('' in n2):
                        n2 = n2.replace("", "")
                n3 = worksheet.cell_value(rowx=curr_row, colx=2)
                n4 = worksheet.cell_value(rowx=curr_row, colx=3)
                row = [n1, n2, n3, n4, a1, a2, a3]

                # Escritura de todos los campos en csv
                spamwriter.writerow(row)
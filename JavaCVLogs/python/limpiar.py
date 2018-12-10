# Librerías necesarias
import xlrd
import unicodecsv as csv
import sys
from datetime import datetime, timedelta

# Lectura del Excel con los logs
workbook = xlrd.open_workbook('./python/ficheros/' + sys.argv[1] + '.xlsx')
worksheet = workbook.sheet_by_index(0)
num_rows = worksheet.nrows - 1
curr_row = 0

with open('LogsAmpliado.csv', 'wb') as csvfile:
        spamwriter = csv.writer(csvfile, delimiter=',', quotechar='"', quoting=csv.QUOTE_ALL)
        spamwriter.writerow(["fecha", "hora", "contexto", "componente", "nombre", "user id", "descripcion", "module id"])
        # Escritura en csv con las columnas ampliadas de la descripción
        while curr_row < num_rows:

                # Indice++
                curr_row += 1
                
                # Inicialización de los campos ya existentes
                fecha = worksheet.cell_value(rowx=curr_row, colx=0)
                print(fecha)
                hora = fecha.split(" ")[1]
                fecha = fecha.split(" ")[0]
                if (len(fecha) == 9):
                        fecha = "0" + fecha
                n3 = worksheet.cell_value(rowx=curr_row, colx=1)
                if ('' in n3):
                        n3 = n3.replace("", "") # Elimina caracteres especiales
                n4 = worksheet.cell_value(rowx=curr_row, colx=2)
                n5 = worksheet.cell_value(rowx=curr_row, colx=3)

                analizar = True

                if (sys.argv[2] == "fecha"):
                   fechaInicio = datetime(int(sys.argv[3][6:]),int(sys.argv[3][3:5]),int(sys.argv[3][0:2]))
                   fechaFin = datetime(int(sys.argv[4][6:]),int(sys.argv[4][3:5]),int(sys.argv[4][0:2]))
                   if (fechaInicio > datetime(int(fecha[6:]),int(fecha[3:5]),int(fecha[0:2])) or datetime(int(fecha[6:]),int(fecha[3:5]),int(fecha[0:2])) > fechaFin):
                           analizar = False 

                if (analizar):
                        # Inicialización de los tres campos nuevos.
                        a1 = ""
                        a2 = ""
                        a3 = "NULL"

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
                        row = [fecha, hora, n3, n4, n5, a1, a2, a3]

                        # Escritura de todos los campos en csv
                        spamwriter.writerow(row)
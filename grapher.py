import matplotlib.pyplot as plt 
from cycler import cycler
import csv 
import time
import numpy as np

def read_csv(filename):
    header = []
    names = []
    data = []
    with open(filename) as f: 
        reader = csv.reader(f)
        first = True
        for row in reader:
            if first:
                header = row[1:]
                first = False
            else:
                line = []
                for cell in row:
                    try:
                        line.append(float(cell))
                    except ValueError:
                        names.append(cell)
                data.append(line)

    return header, names, data

def read_csv_linear(filename):
    data = []
    with open(filename) as f:
        reader = csv.reader(f)
        first = True
        
        for row in reader:
            if first:
                first = False
                continue
            
            line = []
            for cell in row:
                try:
                    line.append(float(cell))
                except ValueError:
                    pass
            data.append(line)

    return data
        

def plot_data(header, names, data, linear_data=None):
    # Définir une liste de couleurs. Utilisez plt.cm.tab10, plt.cm.Set3, etc., pour plus de couleurs.
    colors = plt.cm.tab10(range(len(data)))

    plt.figure(figsize=(12, 8))
    
    for i, (line, color) in enumerate(zip(data, colors)):
        label = f'concurrent {names[i]}'
        x = range(len(line))
        y = line

        degree = 5
        coefficients = np.polyfit(x, y, degree)
        poly_model = np.poly1d(coefficients)
        x_lin = np.linspace(min(x), max(x), 500)
        y_pred = poly_model(x_lin)

        plt.plot(x_lin, y_pred, color=color, label=label)
        plt.scatter(x, y, color=color)
        
        if linear_data is not None:
            y = linear_data[i][0]
            label = f'linear {names[i]}'
            # Utilisation de la même couleur pour la ligne horizontale
            plt.hlines(y, 0, len(header)-1, colors=color, linestyles='dashed', label=label)

    plt.xticks(range(len(header)), header)
    plt.xlabel("Batch size")
    plt.ylabel("Time [ms]")
    plt.legend(framealpha=1.0)

    if save:
        import platform
        osname = platform.system()
        filename = "./results/result_" + osname + "_" + str(int(time.time()*1000)) + ".pdf"
        plt.savefig(filename)
        print("INFO saved the figure in " + filename)
    else:
        plt.show()

def plot_data_(header, names, data, linear_data = None, save = False):
    i = 0
    for line in data:
        label = 'concurrent ' + names[i]
        plt.plot(line, label=label)

        if linear_data is not None:
            y = linear_data[i][0]
            label = 'linear ' + names[i]
            plt.hlines(y, 0, len(header)-1, linestyles='dotted', label=label)

        i += 1

    plt.xticks(range(len(header)), header)
    plt.xlabel("Batch size")
    plt.ylabel("Time [ms]")
    plt.legend()

    plt.show()
        
if __name__ == "__main__":
    save = False
    import sys 
    if len(sys.argv) < 2:
        print("ERROR : missing required filename argument")
        exit(1)

    if len(sys.argv) >= 3:
        lin_fn = sys.argv[2]
        lindata = read_csv_linear(lin_fn)
    else:
        lindata = None

    if len(sys.argv) == 4 and sys.argv[3] == "--save":
        save = True


    filename = sys.argv[1]
    plot_data(*read_csv(filename), lindata)

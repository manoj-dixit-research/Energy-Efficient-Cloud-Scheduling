# Energy-Efficient-Cloud-Scheduling
Implementation of an Energy-Efficient Resource Provisioning framework in Cloud Data Centers using Hybrid GA-CNN Optimization. Built with CloudSim 4.0, ND4J, and DL4J
# Energy-Efficient Cloud Resource Provisioning using GA-CNN Optimization

## Project Overview
This project presents an advanced framework for **Resource Provisioning in Cloud Data Centers**[cite: 3]. It utilizes a hybrid optimization approach combining **Genetic Algorithms (GA)** for task clustering[cite: 1] and **Convolutional Neural Networks (CNN)** for dynamic scheduling decisions[cite: 6]. The simulation is powered by **CloudSim 4.0**[cite: 11] and uses real-world workloads from the **PlanetLab** dataset[cite: 13].

##  Core Methodology
* **Task Clustering:** Uses GA and K-Means to group incoming user requests based on resource intensity[cite: 1, 2].
* **Intelligent Scheduling:** A CNN model (built with DL4J/ND4J) predicts the most efficient VM mapping for each cluster[cite: 6, 12, 15].
* **Resource Modeling:** Virtual Datacenters, Hosts, and VMs are modeled to reflect real cloud environments[cite: 5, 9].

## Performance Metrics
The system evaluates the "Proposed" model against benchmark algorithms like ABC, PSO, and Round Robin across several metrics[cite: 20, 24, 25]:
- **Makespan Optimization**[cite: 25]
- **Energy Efficiency (kWh)**[cite: 23]
- **SLA Violation Rate (%)**[cite: 20]
- **Resource Utility (%)**[cite: 26]

## Setup Instructions
### Prerequisites
- **Java Development Kit (JDK) 8+**
- **IDE:** IntelliJ IDEA or Eclipse

### Dependencies
All required JAR files are located in the `/lib` folder[cite: 11, 12, 15]:
1. `cloudsim-4.0.jar`
2. `nd4j-api-0.9.0.jar`
3. `deeplearning4j-core-0.4.0.jar`
4. `jfreechart-1.0.9.jar` & `jcommon-1.0.17.jar`

### How to Run
1. Clone this repository to your local machine.
2. Import the project into your IDE.
3. Add all JARs from the `/lib` folder to your project's **Build Path/Libraries**.
4. Verify the dataset path in `Code/Main.java` points to the `/workload` folder[cite: 3].
5. Execute `Code.Main.java`[cite: 3].

## 📝 Author
**Manoj Kumar Dixit**
PhD Scholar, NIT Jamshedpur

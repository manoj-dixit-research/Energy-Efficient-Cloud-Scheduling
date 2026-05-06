Energy-Efficient Resource Provisioning in Cloud Data Centers
🚀 Project Overview
This repository contains the implementation of an Effective Resource Provisioning Mechanism (Eff-RPM) designed for large-scale cloud data centers. The project addresses the critical challenge of energy efficiency and resource management by utilizing a hybrid optimization strategy.

Our framework integrates Enhanced Genetic K-means (EGKM) for workload clustering and a Hybrid Optimal Convolutional Neural Network (HOCNN) for intelligent resource allocation. The simulation is conducted using the CloudSim platform with real-world traces from the PlanetLab and Bitbrains datasets.

🧠 Key Features & Methodology
Workload Pre-processing: Eliminates noisy and invalid requests while assigning unique identifiers to manage Service Level Agreements (SLA) effectively.

Intelligent Clustering (EGKM): Categorizes workloads based on Quality of Service (QoS) requirements, significantly reducing makespan and computation time.

Deep Learning Prediction: A CNN-based model predicts future CPU utilization and workload bursts to enable proactive resource scaling.

Cuckoo Search Optimization (CSO): Optimally provisions resources by balancing multiple parameters: Response Time, Makespan, Resource Utility, and Energy Consumption.

📈 Performance Results
The proposed Eff-RPM outperforms existing methods (like ABC, PSO, and BULLET) across all major benchmarks:

Energy Consumption: Reduced to 76.504 kWh.

Resource Utilization: Achieved a high efficiency of 88.922%.

SLA Violation Rate: Maintained at a low 6.541%.

Response Time: Optimized to 278.84 s.

🛠️ Setup & Execution
Clone the Repo: Download the source code and datasets.

Configure Libraries: Add all JAR files from the /lib folder (CloudSim, DL4J, ND4J, JFreeChart) to your IDE's Build Path.

Set Data Path: Ensure the workload path in Main.java points to your local /workload folder.

Run: Execute Code.Main.java to view performance graphs.

📖 Research Publication
This work is based on the research paper published in Sustainable Computing: Informatics and Systems (SUSCOM), Elsevier (2025).

Title: Energy-efficient resource provisioning in cloud data centers

Authors: Manoj Kumar Dixit, Dilip Kumar

Journal: Sustainable Computing: Informatics and Systems, Volume 47

"The proposed GA-CNN model shows a ~20% reduction in energy consumption and a significant improvement in resource utility (~89%) compared to benchmark algorithms like BULLET, SMO, and ABC."

Why this research matters:
Academic Rigor: Published in a high-impact Elsevier journal, ensuring the methodology has undergone strict peer review.

Sustainability Focus: The research directly contributes to "Green Computing" by proposing a model that slashes energy waste in global data centers.

Hybrid Innovation: It is one of the few frameworks that successfully hybridizes evolutionary algorithms (Genetic) with Deep Learning (CNN) to solve real-time scheduling conflicts.

Real-world Applicability: Beyond simulation, this logic is designed for practical applications like interactive online gaming, financial trading, and real-time analytics.

Author: Manoj Kumar Dixit

PhD Scholar, Department of CSE, National Institute of Technology (NIT) Jamshedpur.

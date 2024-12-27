import os
import shutil

from PIL import Image
from sklearn.metrics import f1_score, precision_score, recall_score

import torch
import torch.nn as nn
from torch.utils.data import DataLoader
from torchvision import models
from torchvision.models import ResNet18_Weights



def clean_dataset(raw_images_directory: str, classes: list[str]):
    """
    Take the VOC2012 dataset's images and the VOC2012's Image Sets and organize the images into "train" and "val" sets.
    Within the "train" and "val" directories are separate directories for each class.

    :param raw_images_directory: The root directory that the raw images are copied to
    :param classes: The classes to be used for the model
    """

    print("Cleaning dataset...")

    # Pathing Variables
    JPG_IMAGES_DIR = "VOC2012 Images"   # Folder with all .jpg images in the VOC2012 Dataset
    IMAGE_SETS_DIR = "VOC2012 Sets"     # Folder with .txt files specifying which images belong to which classes

    # If the raw_images_directory already exists, remove it
    if os.path.exists(raw_images_directory):
        shutil.rmtree(raw_images_directory)

    # Create an output directory that will store the raw train/val images for each selected class (Raw Images)
    os.makedirs(raw_images_directory, exist_ok=True)

    # Create a subdirectory that will store all relevant images within the output directory (Raw Images/all)
    all_folder_path = os.path.join(raw_images_directory, "all")
    os.makedirs(all_folder_path, exist_ok=True)

    # Create a subdirectory for train and val subsets within the output directory (Raw Images/train), (Raw Images/val)
    for subset in ['train', 'val']:
        subset_folder = os.path.join(raw_images_directory, subset)
        os.makedirs(subset_folder, exist_ok=True)

        # Create sub-subdirectories for each class within each subset (ex: Raw Images/train/cat)
        for class_name in classes:
            class_folder = os.path.join(raw_images_directory, subset, class_name)
            os.makedirs(class_folder, exist_ok=True)

    # Loop through each .txt file in VOC2012 Sets directory. For each .txt file...
    for filename in os.listdir(IMAGE_SETS_DIR):

        # Get the class and subset of the image set
        img_set_tokens = filename.split("_")
        img_class, img_subset = img_set_tokens[0], img_set_tokens[1][:-4]

        # If the .txt file is for a class not to be trained on, continue
        if img_class not in classes:
            continue

        # Read through each .txt file and move the images to the corresponding directories
        with open(os.path.join(IMAGE_SETS_DIR, filename), 'r') as img_set_txt_file:

            # For each .jpg file name in the .txt file...
            for jpg_file_name in img_set_txt_file:

                # Extract the image's file name and its label (belongs to the class or not)
                tokens = jpg_file_name.strip().split()
                image_file_name, label = tokens[0], int(tokens[1])

                # If that image is labeled as part of the class...
                if label == 1:

                    # Copy the image over to its respective (train|val)/(class) directory
                    src_img_path = os.path.join(JPG_IMAGES_DIR, f"{image_file_name}.jpg")
                    dest_img_path = os.path.join(raw_images_directory, img_subset, img_class, f"{image_file_name}.jpg")
                    shutil.copy(src_img_path, dest_img_path)

                    # Add the image to the folder that contains all images
                    shutil.copy(src_img_path, all_folder_path)

    # Finished
    print("Finished cleaning dataset\n")



def get_image_labels(classes: list[str], raw_images_directory: str):
    """
    Function to one-hot encode each image, assigning each file name with a vector of 1s and 0s or 2s and 0s that
    dictates which classes are present in the image.

    :param classes: The classes to classify an image by
    :param raw_images_directory: File path to the Raw Images directory
    :return: An array storing tuples of (file path, label)
    """

    print("Assigning labels...")

    # Store all finished labels
    all_labels = []

    # For each image...
    all_dir_path = f"{raw_images_directory}/all"
    for img_path in os.listdir(all_dir_path):

        # Store the class labels for the image
        labels = []

        # Loop through each class's train and val subdirectories
        for class_name in classes:

            # Create a file path to check if it exists in either subdirectory
            train_img_path = os.path.join(raw_images_directory, 'train', class_name, img_path)
            val_img_path = os.path.join(raw_images_directory, 'val', class_name, img_path)

            # If the image is found in the class's train directory, append "2"
            if os.path.exists(train_img_path):
                labels.append(2)
            # If the image is found in the class's validation directory, append "1"
            elif os.path.exists(val_img_path):
                labels.append(1)
            # If the image is not found in either, then that class is not present in the image - append 0
            else:
                labels.append(0)

        # Add the image-labels pair to all_labels
        file_path = os.path.join(all_dir_path, img_path)
        all_labels.append((file_path, labels))

    # Return all_labels
    print("Finished assigning labels\n")
    return all_labels



def create_dataloaders(img_path_label_pairs: list[tuple], batch_size: int):
    """
    Create torch DataLoaders, which are batch datasets. Formatting the data into batches is required, as the ResNet18
    model works on batches, not individual images.

    :param img_path_label_pairs: List of tuples containing file names and labels
    :param batch_size: The number of images that should be included in each training batch
    :return: (train_loader, val_loader) - tuple of DataLoaders, one for each of the training and validation datasets
    """

    print("Creating DataLoaders...")

    # Sets for training and validation data
    training_set = []
    validation_set = []

    # Get the image at img_path and transform it to fit ResNet18's preferences
    transform = ResNet18_Weights.IMAGENET1K_V1.transforms()

    # Determine whether each image belongs to training or validation
    for img_path, labels in img_path_label_pairs:

        # Transform the image
        with Image.open(img_path) as img:
            image = transform(img)

        # 2 corresponds to training, as defined in get_image_labels()
        if 2 in labels:
            new_labels = [1 if x == 2 else int(x) for x in labels]  # Normalize back to 1s
            labels = torch.tensor(new_labels, dtype=torch.float)

            training_set.append((image, labels))

        # If there are no 2s in the labels, it is for validation
        else:
            labels = torch.tensor(labels, dtype=torch.float)
            validation_set.append((image, labels))

    # Create a dataloader for each dataset
    training_loader = DataLoader(training_set, batch_size=batch_size, shuffle=True)
    validation_loader = DataLoader(validation_set, batch_size=batch_size)

    # Return the dataloaders
    print("Finished creating DataLoaders\n")
    return training_loader, validation_loader



def create_model(classes: list[str]):
    """
    Function that takes a ResNet18's auto-encoder and repurposes it for the classification of the selected classes

    :param classes: List of classes for the model to be trained on
    :return: model - A ResNet18 model with the external layer reset; to be retrained for the selected classes
    """

    print("Creating model...")

    # Get the number of classes for the model to work with
    num_classes = len(classes)

    # Load the pretrained ResNet18 model
    new_model = models.resnet18(weights=ResNet18_Weights.DEFAULT)

    # Reset the last (fully connected) layer of the pretrained model. It will be retrained
    new_model.fc = nn.Sequential(
        nn.Linear(new_model.fc.in_features, num_classes),
        nn.Sigmoid()    # Allows for a probability to be assigned for each class
    )

    # Return the model
    print("Finished creating model\n")
    return new_model



def train_model(model, train_loader: DataLoader, learning_rate: float):
    """
    :param model: The model to be trained
    :param train_loader: The DataLoader containing the training data
    :param learning_rate: How fast the optimizer learns
    :return: model - The trained model
    """

    print("Training model...")

    # Set the model to training mode
    model.train()

    # Initialize the loss function and an optimizer to update the model's weights
    loss_function = nn.BCEWithLogitsLoss()
    optimizer = torch.optim.Adam(model.parameters(), lr=learning_rate)

    # For each image-label batch pair in the training DataLoader...
    for images, labels in train_loader:

        # Reset the weights for this batch
        optimizer.zero_grad()

        # Get the model's output for the batch
        outputs = model(images)

        # Calculate CrossEntropyLoss
        loss = loss_function(outputs, labels.float())
        loss.backward()

        # Update weights according to the loss
        optimizer.step()

    # Return the model
    print("Finished training model\n")
    return model



def validate_model(trained_model, val_loader: DataLoader, threshold: float = 0.75):
    """
    Function that takes a trained model and a validation dataset and calculates some scores to justify the efficiency
    of the trained model.

    :param trained_model: A trained model to be evaluated
    :param val_loader: A DataLoader containing validation data
    :param threshold: The belief requirement the model needs to have in a class in order to say it is present
    """

    print("Validating model...\n")

    # Set up the model for evaluation
    trained_model.eval()    # Set the model to evaluation mode
    predicted_labels = []   # List to store the model's predictions
    actual_labels = []      # List to store the actual labels

    # For each image-label batch pair in the validation DataLoader...
    for images, labels in val_loader:

        # Get the predicted label for this batch
        outputs = trained_model(images)     # Get the model's output for the batch
        predictions = (outputs > threshold).int()   # If the belief exceeds the threshold, label it 1; else 0

        # Add the predicted and actual labels to their respective lists
        predicted_labels.extend(predictions)
        actual_labels.extend(labels)

    # Calculate validation scores
    precision = precision_score(actual_labels, predicted_labels, average='samples')
    recall = recall_score(actual_labels, predicted_labels, average='samples')
    f1 = f1_score(actual_labels, predicted_labels, average='samples')

    # Print the results to the console
    print("Results:\n-----------------------------------------------")
    print(f"Model Precision: {precision:.3f}")
    print(f"Model Recall: {recall:.3f}")
    print(f"Model F1 Score: {f1:.3f}")
    print("Finished validating model\n")



def classify_image(trained_model, classes: list[str], img_path: str, threshold: float = 0.75):
    """
    Function that uses a trained model to make a prediction on an image

    :param trained_model: The trained model that will classify the image
    :param classes: All classes an image can be classified as
    :param img_path: Path to image to be classified
    :param threshold: The belief requirement the model needs to have in a class in order to say it is present
    :return Those classes which the model has predicted to be present in the image
    """

    print("Classifying image...")

    # Transform the image into a PyTorch tensor that the ResNet18 model can work with
    transform = ResNet18_Weights.IMAGENET1K_V1.transforms()


    # Open the image and transform it into a batch to allow the ResNet18 model to accept it
    with Image.open(img_path) as img:
        img = transform(img).unsqueeze(0)  # Convert the single image into a batch

        # Set the model to evaluation mode and make the prediction
        trained_model.eval()
        output = trained_model(img)
        predictions = list((output > threshold).squeeze())  # Get a list of binary predictions

        # Print the probabilities for each class
        print("\nPredicted Probabilities:")
        for class_name, prob in zip(classes, output.squeeze()):
            print(f"{class_name}: {prob:.4f}")

        # Store all detected classes
        detected_classes = []
        for i in range(len(classes)):
            if predictions[i] == 1:
                detected_classes.append(classes[i])

        # Return the predicted class label
        print(f"\nImage predicted to contain the following class(es):\n {detected_classes}\n")
        return detected_classes



def main(classes):

    # Initialize parameters for data cleaning and normalization
    IMAGES_DIR = "Raw Images"  # Folder where the organized images will be saved

    # Initialize hyperparameters for training the model
    BATCH_SIZE = 32  # Number of images processed in a single batch
    LEARNING_RATE = 0.0001  # Strength of updating the weights
    THRESHOLD = 0.75

    # Filter the VOC2012 Dataset to extract the data for the classes being tested
    clean_dataset(IMAGES_DIR, classes)

    # Get multi-label classifications for each image
    all_img_path_label_pairs = get_image_labels(classes, IMAGES_DIR)

    # Transform the data into training and validation DataLoaders
    train_loader, val_loader = create_dataloaders(all_img_path_label_pairs, BATCH_SIZE)

    # Create the multi-label classification model using the ResNet18 pretrained model
    new_model = create_model(classes)

    # Train the model
    trained_model = train_model(new_model, train_loader, LEARNING_RATE)

    # Validate the model
    validate_model(trained_model, val_loader, THRESHOLD)

    # Save the trained model
    path_to_model = "trained_model.pth"
    torch.save(trained_model.state_dict(), path_to_model)
    print(f"Model saved as '{path_to_model}'\n")

    # Return the trained model
    return trained_model


if __name__ == "__main__":

    """
    List of classes to choose from:
        aeroplane, bicycle, bird, boat, bottle, bus, car, cat, chair, cow, diningtable, dog, horse, motorbike, person,
        pottedplant, sheep, sofa, train, tvmonitor
    """

    # Specify the classes to use in the model
    class_names = ['aeroplane', 'bicycle', 'cat', 'sofa', 'person']

    # Create, train, validate, and save a ResNet18 classification model for the specified classes in the VOC2012 Dataset
    classification_model = main(class_names)

    # OPTIONAL - Load the model that was saved in a previous call to main()
    # classification_model = create_model(class_names)
    # classification_model.load_state_dict(torch.load("trained_model.pth", weights_only=True))
    # classification_model.eval()

    # Make a prediction using the model
    # REPLACE img_to_predict.jpg WITH ANY .jpg IMAGE OF YOUR CHOOSING
    if os.path.exists("img_to_classify.jpg"):
        img_prediction = classify_image(classification_model, class_names, 'img_to_classify.jpg')

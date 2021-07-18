/**
 * RGBImage.java represents a image in using the RGBColor class as a two dimensional array.
 *
 * @author : Daniel Leibner
 * @class: RGBImage
 * @authorId : #########
 * @version: 23/04/21
 */
public class RGBImage {

     /**
      * The Image is an array using RGColor class as the array object.
      */
     private RGBColor[][] _image;



     // -- Constructors --

     /**
      * A constructor that creates a new black image sized by the number of rows and columns
      * Obtained as parameters.
      *
      * @param rows the number of pixel "rows" in the image
      * @param columns the number of pixel "columns" in the image
      */
     public RGBImage(int rows, int columns) {
          this._image = new RGBColor[rows][columns];
          for (int row=0; row< this._image.length;row++){
               for (int column=0;column< this._image[row].length;column++){
                    this._image[row][column]= new RGBColor();
               }
          }
     }

     /**
      * A constructor that creates a new  RGB image that is identical to the array of pixels was given as a parameter.
      *
      * constructor for making a new image as copy from array.
      * height=arrayToMakeImg.length, width=arrayToMakeImg[i].length, content=arrayToMakeImg[i][j]
      *
      * @param arrayToMakeImg the array to copy from.
      */
     public RGBImage(RGBColor[][] arrayToMakeImg) {
          this._image = new RGBColor[arrayToMakeImg.length][arrayToMakeImg[0].length];
          for (int row = 0; row < arrayToMakeImg.length; row++) {
               for (int col = 0; col < arrayToMakeImg[row].length; col++) {
                    this._image[row][col] = arrayToMakeImg[row][col];

               }
          }

     }


     /**
      * A copy constructor that gets a different image, and copies the pixels
      *
      * @param other the image to copy.
      */
     public RGBImage(RGBImage other) {
          this(other.getHeight(), other.getWidth());//constructor of class bay row&columns length.
          for (int row = 0; row < other.getHeight(); row++) {
               for (int col = 0; col < other.getWidth(); col++) {
                    this.setPixel(row, col, other.getPixel(row, col));
               }
          }
     }

     // -- Methods --

     /**
      * Method to get the height of the image.
      *
      * @return the height of image.
      */
     public int getHeight() {
          return (this._image.length);
     }


     /**
      * Method to get the width of the image.
      *
      * @return the width of the image
      */
     public int getWidth() {
          return (this._image[0].length);
     }


     /**
      * private Method to check if place in bounds of image
      *
      * @param row the row
      * @param col the col
      *
      * @return true if in bounds
      */
     private boolean isInBounds(int row, int col) {
          return (0 <= row) && (row < this.getHeight()) && (0 <= col) && (col < this.getWidth());
     }


     /**
      * A method that accepts coordinates in an image,
      *   and returns the pixel found in those coordinates.
      * If the coordinates are out of the image, a black pixel will be returned.
      *
      * @param row the row coordinate of the pixel
      * @param col the column coordinate of the pixel
      *
      * @return the pixel found the coordinates
      */
     public RGBColor getPixel(int row, int col) {
          if (isInBounds(row, col)) {
               return new RGBColor(this._image[row][col]);//returning the RGB point in the given place
          } else {//if the place out of the bounds
               return new RGBColor();//returning black point
          }
     }


     /**
      * A method that accepts coordinates in an image and three colors (pixel),
      *   and sets this pixel to be in coordinates obtained as parameters.
      * If the coordinates are outside the image size, no change will be made.
      *
      * @param row   the row coordinate of the pixel to be replace
      * @param col   the column coordinate of the pixel to be replace
      * @param pixel the pixel to set in given coordinates.
      */
     public void setPixel(int row, int col, RGBColor pixel) {
          if (isInBounds(row, col)) {
               this._image[row][col] = new RGBColor(pixel);
          }

     }

     /**
      * A method that receives an image as a parameter and returns whether the
      *   image identical to the image was applied on.
      *
      * @param other the image to check if identical.
      *
      * @return true if the image are identical
      */
     public boolean equals(RGBImage other) {

          //checks that the two pic in the same scale
          if ((other.getWidth() != this.getWidth()) || (this.getHeight() != other.getHeight())) {
               return false;
          }
          //checks that the two pic have same pixel in same place.
          for (int row = 0; row < other.getHeight(); row++) {
               for (int col = 0; col < other.getWidth(); col++) {
                    if (!(this.getPixel(row, col).equals(other.getPixel(row, col)))) {
                         return false;
                    }
               }
          }
          return true;
     }

     /**
      * A method that turns an image around the vertical axis.
      * (the instruction was to implement the method so that a horizontal inversion
      *   is a reversal according to the vertical axis.
      *   the first column becomes the last column,
      *   the second becomes the second from the end and so on.)
      */
     public void flipHorizontal() {
          final RGBImage TEMPORARY_COPY = new RGBImage(this._image);//temporary copy.
          for (int col = 0; col < this.getWidth(); col++) {
               for (int row = 0; row < this.getHeight(); row++) {
                    this.setPixel(row, col, TEMPORARY_COPY.getPixel(
                            row,//row dont need to bee changed
                            (TEMPORARY_COPY.getWidth() - 1) - col));//sets point((LastCol-colIndex,row) to point(col,row).
               }
          }
     }

     /**
      * A method that turns an image around the horizontal axis.
      * (the instruction was to implement the method so that a vertical
      *   inversion is a reversal according to the horizontal axis.
      *   the first row becomes the last row,
      *   the second becomes the second from the end and so on.)
      *
      */
     public void flipVertical() {
          //if we rotate clockwise the image deg and than flip it horizontal
          // and then rotate the image against clockwise direction we get flip vertical
          this.rotateClockwise();
          this.flipHorizontal();
          this.rotateCounterClockwise();
     }


     /**
      * A method that converts the colors of all the pixels in an image by
      *   replacing each RGB color with its complement to 255.
      *   formula of invert: (red,green,blue).invert = (256-red,256-green,256-blue)
      * For example: RGB values (0,1,2) to RGB (255,254,253)
      *
      */
     public void invertColors() {
               for (int col = 0; col <getWidth() ; col++) {
                    for (int row = 0; row <getHeight() ; row++) {
                         this._image[row][col].invert();

               }
          }
     }


     /**
      * This method rotate the image 90 degree in the counter clockwise direction.
      */
     public void rotateCounterClockwise() {
          final RGBImage TEMPORARY_COPY = new RGBImage(this._image);
          this._image = new RGBColor[this.getWidth()][this.getHeight()];
          for (int row = 0; row < this.getHeight(); row++) {
               for (int col = 0; col < this.getWidth(); col++) {
                    this.setPixel(row, col,
                            TEMPORARY_COPY.getPixel(col, (TEMPORARY_COPY.getWidth() - 1 - row)));
               }

          }
     }

     /**
      * This method rotate the image 90 degree in the clockwise direction.
      */
     public void rotateClockwise() {
          //rotating image three times in 90 deg' in the same direction is
          //like rotate the image on time in 90 deg' to opposite direction.
          this.rotateCounterClockwise();
          this.rotateCounterClockwise();
          this.rotateCounterClockwise();
     }

     /**
      * This method shifts an image right/left in "offset"(number) of pixels.
      *   the formula is: image[row][column] = image[row][column+(offset)] .
      *
      * The columns inserted into the moved columns will be black.
      * If the offset is greater than the number of columns, nothing will be done.
      * If the offset is equal to the number of columns, the whole image will turn black.
      *
      * @param offset the number of columns to shift(positive number = right)
      */
     public void shiftCol(int offset) {
          if(Math.abs(offset)<=this.getWidth()){
               final RGBImage TEMPORARY_COPY = new RGBImage(this._image);
                    for (int row = 0; row < this.getHeight(); row++) {
                       for (int col = 0; col < this.getWidth(); col++) {
                          this.setPixel(row, col,TEMPORARY_COPY.getPixel(row, (col - (offset))));
                    }
               }
          }

     }


     /**
      * This method shifts an image up/down in "offset"(number) of pixels.
      *   the formula is: image[row][column] = image[row+(offset)][column] .
      *
      * The rows inserted into the moved rows will be black.
      * If the offset is greater than the number of rows, nothing will be done.
      * If the offset is equal to the number of rows, the whole image will turn black.
      *
      * @param offset the number of rows to shift(positive number = up)
      */
     public void shiftRow(int offset) {
          //rotating(counter clock wise) the image makes rows to be columns
          // and than using the shift column method will shift bay offset
          //    the columns(the transformed rows),
          //    and rotating back will make image as the original image shifted bay offset rows.
          this.rotateCounterClockwise();
          this.shiftCol(offset);
          this.rotateClockwise();
          }


     /**
      * A method that returns a gray representation of the picture
      * The gray value is set as:
      *        30% of the red color +
      *        59% of the green color +
      *        11% of the blue color.
      *
      * @return gray representation of the image.
      */
     public double[][] toGrayscaleArray() {
          double[][] grayscaleArray = new double[this.getHeight()][this.getWidth()];
          for (int row = 0; row < this.getHeight(); row++) {
               for (int col = 0; col < this.getWidth(); col++) {
                    grayscaleArray[row][col] = this.getPixel(row, col).convertToGrayscale();
               }
          }
          return grayscaleArray;
     }

     /**
      * A method that returns a string of characters representing the image.
      * The string format will be:
      *   each line in the array is in a separate line with a single space between the pixels.
      *   at the end of a line there is no space.
      *   each pixel is displayed in a round parentheses contains a triangular string of
      *   characters(R,G,B) separated by commas.
      *
      * @return string of characters representing the picture
      */
     public String toString() {
          String output = "";
          for (int row = 0; row < this.getHeight(); row++) {
               for (int col = 0; col < this.getWidth() ; col++) {
                    if (col == 0 && row != 0)
                         output += "\n";
                    if (col==this.getWidth()-1){
                         output += this.getPixel(row, col).toString();
                    }else{
                         output += this.getPixel(row, col).toString() + " ";
                    }
               }
          }
          output += "\n";//for the last row
          return output;
     }

     /**
      * A method that returns a copy of the array of pixels.
      *
      * @return copy of the array of pixels.
      */
     public RGBColor[][] toRGBColorArray() {
          RGBColor[][] RGBColorArray = new RGBColor[this.getHeight()][this.getWidth()];
          for (int row = 0; row < this.getHeight(); row++) {
               for (int col = 0; col < this.getWidth(); col++) {
                    RGBColorArray[row][col] = new RGBColor(this.getPixel(row, col));
               }
          }
          return RGBColorArray;
     }
}




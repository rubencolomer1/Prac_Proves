package services;
import Exceptions.NoFacialPointsException;
import data.BiometricData;
public interface BiometricReader {
    BiometricData readBiometricData() throws NoFacialPointsException;
}